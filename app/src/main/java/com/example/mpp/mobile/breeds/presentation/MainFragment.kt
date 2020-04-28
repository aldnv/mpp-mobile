package com.example.mpp.mobile.breeds.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mpp.mobile.R
import com.example.mpp.mobile.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.scope.lifecycleScope
import org.koin.core.parameter.parametersOf


class MainFragment: Fragment() {

    private lateinit var binding: MainFragmentBinding

    private val viewmodel: BreedViewModel by lifecycleScope.inject{ parametersOf(this) }

    private val adapter: BreedAdapter by lifecycleScope.inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
        adapter.onClickListener = {name ->
            viewmodel.onBreedClick(name)
        }
        viewmodel.data.observe(viewLifecycleOwner, ::show)
    }

    private fun show(state: BreedViewState){

        binding.progressBar.isVisible = state == BreedViewState.Loading

        when (state){
            BreedViewState.Error -> Snackbar
                .make(binding.root, R.string.error_text, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.error_action_text){
                    viewmodel.load()
                }.show()

            is BreedViewState.Success -> {
                adapter.breedList = state.value
            }
        }

    }

}