package com.example.smartawning.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.smartawning.R
import com.example.smartawning.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding = _binding
    private lateinit var controller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        _binding = binding

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        controller = navHostFragment.navController

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(controller)
        supportActionBar?.title = resources.getString(R.string.toolbar_title)


        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if(controller.currentBackStackEntry != null)
                    controller.navigateUp()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}