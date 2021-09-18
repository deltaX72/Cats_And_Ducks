package com.litil.catsandducks.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.litil.appComponent
import com.litil.catsandducks.databinding.FragmentBeforeShiftBinding
import com.litil.catsandducks.presentation.fragments.navigation.navigator
import com.litil.catsandducks.presentation.utils.ClickedButton
import com.litil.catsandducks.presentation.utils.Options
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class BeforeShiftFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentBeforeShiftBinding.inflate(inflater, container, false)

        binding.apply {
            btnShowCat.setOnClickListener { downloadCatImage() }
            btnShowDuck.setOnClickListener { downloadDuckImage() }
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    private fun downloadCatImage() {
        showImage(Options(ClickedButton.SHOW_CAT))
    }

    private fun downloadDuckImage() {
        showImage(Options(ClickedButton.SHOW_DUCK))
    }

    private fun showImage(options: Options) {
        navigator().showImage(options)
    }
}