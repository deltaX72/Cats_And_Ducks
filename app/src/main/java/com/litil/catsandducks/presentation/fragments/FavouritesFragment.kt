package com.litil.catsandducks.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.litil.appComponent
import com.litil.catsandducks.databinding.FragmentFavouritesBinding
import com.litil.catsandducks.domain.models.db.ImageModel
import com.litil.catsandducks.presentation.adapters.FavouritesAdapter
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class FavouritesFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var factory: MainViewModel.Factory

    private val viewModel: MainViewModel by activityViewModels {
        factory
    }

//    @Inject
//    lateinit var factory: MainViewModel.Factory
//
//    private val viewModel by requireActivity().viewModels<MainViewModel> {
//        factory
//    }

    private val adapter = FavouritesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentFavouritesBinding.inflate(inflater, container, false)

        recyclerView = binding.favouriteImagesList
        recyclerView.adapter = adapter

        viewModel.getAllImagesFromDatabase()
        viewModel.ldImageModelList.observe(viewLifecycleOwner, ::loadImages)

        return binding.root
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun loadImages(list: List<ImageModel>) {
        adapter.imagesList = list
    }
}