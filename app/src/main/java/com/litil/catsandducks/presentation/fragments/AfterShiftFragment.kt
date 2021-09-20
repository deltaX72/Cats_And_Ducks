package com.litil.catsandducks.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.litil.appComponent
import com.litil.catsandducks.databinding.FragmentAfterShiftBinding
import com.litil.catsandducks.presentation.utils.ClickedButton
import com.litil.catsandducks.presentation.utils.Options
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class AfterShiftFragment : Fragment() {

    @Inject
    lateinit var factory: MainViewModel.Factory

    private val viewModel: MainViewModel by activityViewModels {
        factory
    }

    private lateinit var binding: FragmentAfterShiftBinding

    private lateinit var options: Options

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        options = savedInstanceState
            ?.getParcelable(KEY) ?: arguments?.getParcelable(ARG) ?: throw IllegalArgumentException(
            "Error!"
        )

        binding = FragmentAfterShiftBinding.inflate(inflater, container, false)

        binding.apply {
            btnShowCat.setOnClickListener { onShowCatButtonClicked() }
            btnShowDuck.setOnClickListener { onShowDuckButtonClicked() }
            ivImage.setOnClickListener { onImageViewDoubleClicked() }
        }

        viewModel.ldModelResponse.observe(viewLifecycleOwner) {
            viewModel.loadImageFromModel(it, binding.ivImage)
        }

        viewModel.ldImageView.observe(viewLifecycleOwner) {
            binding.ivImage.setImageDrawable(it.drawable)
        }

        when (options.clickedButton) {
            ClickedButton.SHOW_CAT -> onShowCatButtonClicked()
            ClickedButton.SHOW_DUCK -> onShowDuckButtonClicked()
            else -> Unit
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY, options)
    }

    private fun onShowCatButtonClicked() {
        viewModel.downloadCatImage()
    }

    private fun onShowDuckButtonClicked() {
        viewModel.downloadDuckImage()
    }

    private fun onImageViewDoubleClicked() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - viewModel.getLastClickTimeValue() < 1000) {
            if (viewModel.getLastCountClicksValue() == 1) {
                viewModel.setLastCountClicksValue(1)
                Toast.makeText(context, "You liked this image!", Toast.LENGTH_SHORT).show()
                viewModel.saveImage(binding.ivImage)
            }
        } else {
            viewModel.setLastCountClicksValue(1)
            viewModel.setLastClickTimeValue(currentTime)
        }
    }

    companion object {
        @JvmStatic
        private val ARG = "ARG"

        @JvmStatic
        private val KEY = "KEY"

        @JvmStatic
        fun instance(options: Options): AfterShiftFragment {
            val args = Bundle()
            args.putParcelable(ARG, options)
            val fragment = AfterShiftFragment()
            fragment.arguments = args
            return fragment
        }
    }
}