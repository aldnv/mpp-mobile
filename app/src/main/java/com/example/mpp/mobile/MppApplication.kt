package com.example.mpp.mobile

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mpp.mobile.data.repository.BreedRepositoryImpl
import com.example.mpp.mobile.data.source.BreedsApi
import com.example.mpp.mobile.data.source.BreedsDataSource
import com.example.mpp.mobile.domen.BreedRepository
import com.example.mpp.mobile.domen.usecase.GetBreeds
import com.example.mpp.mobile.breeds.navigation.NavigatorImpl
import com.example.mpp.mobile.breeds.presentation.BreedAdapter
import com.example.mpp.mobile.navigator.Navigator
import com.example.mpp.mobile.breeds.presentation.BreedViewModel
import com.example.mpp.mobile.breeds.presentation.BreedViewModelFactory
import com.example.mpp.mobile.breeds.presentation.MainFragment
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

@Suppress("unused")
class MppApplication: Application() {

    private val appModule = module {
        single{BreedsApi()}
        single{BreedsDataSource(get())}
        single<BreedRepository> { BreedRepositoryImpl(get()) }
        factory { GetBreeds(get()) }

        scope(named<MainFragment>()){

            scoped {(fragment: Fragment) ->
                ViewModelProvider(fragment)[NavigatorImpl::class.java]
            } bind Navigator::class

            factory {
                BreedAdapter()
            }

            factory<ViewModelProvider.Factory> {
                BreedViewModelFactory(get(), get())
            }

            scoped{ (fragment: Fragment) ->
                val navigatorImpl: NavigatorImpl = get{parametersOf(fragment)}
                navigatorImpl.setContext(fragment)
                ViewModelProvider(fragment, get())[BreedViewModel::class.java]
            }

        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MppApplication)
            modules(appModule)
        }
    }
}