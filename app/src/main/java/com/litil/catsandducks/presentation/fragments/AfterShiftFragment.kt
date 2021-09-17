package com.litil.catsandducks.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.litil.catsandducks.R
import com.litil.catsandducks.presentation.viewmodels.MainViewModel

class AfterShiftFragment(
    val viewModel: MainViewModel
) : Fragment() {

    private lateinit var ivImage: ImageView
    private lateinit var btnShowCat: AppCompatButton
    private lateinit var btnShowDuck: AppCompatButton

//    private val viewModel: MainViewModel by activityViewModels()

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_after_shift, null)

        ivImage = view.findViewById(R.id.ivImage)
        btnShowCat = view.findViewById(R.id.btnShowCat)
        btnShowDuck = view.findViewById(R.id.btnShowDuck)

        viewModel.ldImage.observe(viewLifecycleOwner) {
            viewModel.loadImage(it, ivImage)
        }
        viewModel.ldLastClickTime.observe(viewLifecycleOwner) {

        }
        viewModel.ldLastCountClicks.observe(viewLifecycleOwner) {

        }

        btnShowCat.setOnClickListener {
            viewModel.downloadCatImage()
        }
        btnShowDuck.setOnClickListener {
            viewModel.downloadDuckImage()
        }

        ivImage.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - viewModel.getLastClickTimeValue() < 1000) {
                if (viewModel.getLastCountClicksValue() == 1) {
                    viewModel.setLastCountClicksValue(1)
                    Toast.makeText(context, "You liked this image!", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.setLastCountClicksValue(1)
                viewModel.setLastClickTimeValue(System.currentTimeMillis())
            }
        }

        return view
    }
}