package com.litil.catsandducks.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import com.litil.appComponent
import com.litil.catsandducks.R
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var ivAnimal: ImageView
    private lateinit var btnShowCat: AppCompatButton
    private lateinit var btnShowDuck: AppCompatButton

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        setContentView(R.layout.activity_main)

        initViews()

        viewModel.ldJsonImage.observe(this) {
            viewModel.downloadImageFromUrl(it.url, ivAnimal)
        }

        btnShowCat.setOnClickListener {
            viewModel.downloadCatImage()
        }
        btnShowDuck.setOnClickListener {
            viewModel.showDuck()
        }
    }

    private fun initViews() {
        ivAnimal = findViewById(R.id.ivAnimal)
        btnShowCat = findViewById(R.id.btnShowCat)
        btnShowDuck = findViewById(R.id.btnShowDuck)
    }
}