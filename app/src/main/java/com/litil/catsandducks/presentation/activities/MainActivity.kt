package com.litil.catsandducks.presentation.activities

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.litil.appComponent
import com.litil.catsandducks.R
import com.litil.catsandducks.domain.models.CatImageResponse
import com.litil.catsandducks.domain.models.DuckImageResponse
import com.litil.catsandducks.presentation.fragments.AfterShiftFragment
import com.litil.catsandducks.presentation.fragments.BeforeShiftFragment
import com.litil.catsandducks.presentation.fragments.MainFragment
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import java.lang.RuntimeException
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var container: FrameLayout
    private lateinit var fragmentBeforeShift: BeforeShiftFragment
    private lateinit var fragmentAfterShift: AfterShiftFragment

    private val BEFORE_SHIFT_FRAGMENT_TAG = "BEFORE_SHIFT_FRAGMENT_TAG"
    private val AFTER_SHIFT_FRAGMENT_TAG = "AFTER_SHIFT_FRAGMENT_TAG"

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        setContentView(R.layout.activity_main)
        container = findViewById(R.id.container)

        fragmentBeforeShift = BeforeShiftFragment(viewModel)
        fragmentAfterShift = AfterShiftFragment(viewModel)

        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(
                R.id.container,
                fragmentBeforeShift,
                BEFORE_SHIFT_FRAGMENT_TAG
            )
            fragmentTransaction.commit()
        }

        viewModel.ldIsAnyButtonPressed.observe(this) {
            if (it) {
                replaceFragments(R.id.container, fragmentAfterShift, AFTER_SHIFT_FRAGMENT_TAG)
            }
        }
    }

    private fun replaceFragments(containerId: Int, fragment: Fragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                containerId,
                fragment,
                tag
            )
            fragmentTransaction.commit()
        } else {
            throw RuntimeException("Fragment is not initialized!")
        }
    }

    private fun replaceFragments() {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentAfterShift.tag)
        if (fragment == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.container,
                fragmentAfterShift,
                fragmentAfterShift.tag
            )
            fragmentTransaction.commit()
            Toast.makeText(this, "fragmentAfterShift has been created", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "fragmentAfterShift has already created", Toast.LENGTH_SHORT).show()
        }
    }
}