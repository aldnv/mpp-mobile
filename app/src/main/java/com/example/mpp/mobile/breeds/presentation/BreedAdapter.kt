package com.example.mpp.mobile.breeds.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mpp.mobile.databinding.BreedItemBinding
import com.example.mpp.mobile.domen.model.Breed

class BreedAdapter: RecyclerView.Adapter<BreedViewHolder>() {

    var breedList: List<Breed> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onClickListener: ((Breed) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val breedView = BreedItemBinding.inflate(layoutInflater, parent, false)
        return BreedViewHolder(breedView)
    }

    override fun getItemCount(): Int {
        return breedList.size
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breedList[position]
        holder.bind(breed)
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(breed)
        }
    }
}

