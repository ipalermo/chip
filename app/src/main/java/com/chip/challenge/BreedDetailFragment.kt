package com.chip.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.chip.challenge.adapters.PhotosAdapter
import com.chip.challenge.databinding.FragmentBreedDetailBinding
import com.chip.challenge.viewmodels.BreedDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A fragment representing a single Breed detail screen.
 */
@AndroidEntryPoint
class BreedDetailFragment : Fragment() {

    private val adapter = PhotosAdapter()
    private val args: BreedDetailFragmentArgs by navArgs()

    @Inject
    lateinit var breedDetailViewModelFactory: BreedDetailViewModel.AssistedFactory

    private val breedDetailViewModel: BreedDetailViewModel by viewModels {
        BreedDetailViewModel.provideFactory(
            breedDetailViewModelFactory,
            args.breedName
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentBreedDetailBinding>(
            inflater,
            R.layout.fragment_breed_detail,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner

            photoList.adapter = adapter
            subscribeUi(adapter)

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
            toolbar.title = args.breedName

        }

        return binding.root
    }

    private fun subscribeUi(adapter: PhotosAdapter) {
        lifecycleScope.launch {
            breedDetailViewModel.searchPictures().collectLatest { photos ->
                adapter.submitList(photos)
            }
        }
    }
}
