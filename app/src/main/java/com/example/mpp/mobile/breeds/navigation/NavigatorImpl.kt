package com.example.mpp.mobile.breeds.navigation

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mpp.mobile.domen.model.Breed
import com.example.mpp.mobile.event.Event
import com.example.mpp.mobile.event.EventObserver
import com.example.mpp.mobile.navigator.Navigator

class NavigatorImpl(): ViewModel(), Navigator {

    private val mutableEvent = MutableLiveData<Event<Breed>>()

    fun setContext(fragment: Fragment){
        //val context = fragment.requireContext().applicationContext
        mutableEvent.observe(fragment.viewLifecycleOwner,
            EventObserver {
                Toast.makeText(fragment.requireContext().applicationContext, "Not implemented", Toast.LENGTH_SHORT).show()
            })
    }

    override fun openImageList(breed: Breed) {
        mutableEvent.value = Event.single(breed)
    }
}