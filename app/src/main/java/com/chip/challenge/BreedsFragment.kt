package com.chip.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chip.challenge.adapters.BreedAdapter
import com.chip.challenge.databinding.FragmentBreedsBinding
import com.chip.challenge.util.SpringAddItemAnimator
import com.chip.challenge.viewmodels.BreedsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedsFragment : Fragment() {

    private val viewModel: BreedsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBreedsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val breedsAdapter = BreedAdapter()
        with(binding.breedsList) {
            adapter = breedsAdapter
            itemAnimator = SpringAddItemAnimator()
        }
        subscribeUi(breedsAdapter)

        return binding.root
    }

    private fun subscribeUi(adapter: BreedAdapter) {
        viewModel.breeds.observe(viewLifecycleOwner) { breeds ->
            adapter.submitList(breeds)
        }
    }
}
