package com.chip.challenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chip.challenge.BreedsFragment
import com.chip.challenge.BreedsFragmentDirections
import com.chip.challenge.data.Breed
import com.chip.challenge.databinding.ListItemBinding

/**
 * Adapter for the [RecyclerView] in [BreedsFragment].
 */

class BreedAdapter : ListAdapter<Breed, RecyclerView.ViewHolder>(BreedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BreedViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val breed = getItem(position)
        (holder as BreedViewHolder).bind(breed)
    }

    class BreedViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.breed?.let { breed ->
                    navigateToBreed(breed, it)
                }
            }
        }

        private fun navigateToBreed(
            breed: Breed,
            view: View
        ) {
            val direction =
                BreedsFragmentDirections.actionBreedsFragmentToBreedDetailFragment(
                    breed.name
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Breed) {
            binding.apply {
                breed = item
                executePendingBindings()
            }
        }
    }
}

private class BreedDiffCallback : DiffUtil.ItemCallback<Breed>() {

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}
