package com.example.mpp.mobile.breeds.presentation

import androidx.recyclerview.widget.RecyclerView
import com.example.mpp.mobile.databinding.BreedItemBinding
import com.example.mpp.mobile.domen.model.Breed

class BreedViewHolder(private val binding: BreedItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(breed: Breed){
        binding.breedItemTitle.text = breed.name
    }
}