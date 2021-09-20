package com.litil.catsandducks.presentation.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.litil.appComponent
import com.litil.catsandducks.R
import com.litil.catsandducks.databinding.ActivityMainBinding
import com.litil.catsandducks.presentation.fragments.AfterShiftFragment
import com.litil.catsandducks.presentation.fragments.BeforeShiftFragment
import com.litil.catsandducks.presentation.fragments.FavouritesFragment
import com.litil.catsandducks.presentation.fragments.navigation.Navigator
import com.litil.catsandducks.presentation.utils.ClickedButton
import com.litil.catsandducks.presentation.utils.Options
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.bottomNavigation.setOnItemSelectedListener(::onBottomNavigationItemClicked)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, BeforeShiftFragment())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun updateUI() {

    }

    override fun showImage(options: Options) {
        startFragment(AfterShiftFragment.instance(options))
    }

    override fun showFavourites() {
        startFragment(FavouritesFragment())
    }

    private fun onBottomNavigationItemClicked(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main -> {
                showImage(Options(ClickedButton.NONE))
            }
            R.id.favourites -> {
                showFavourites()
            }
            else -> return false
        }
        return true
    }
}