package com.example.smartawning.ui

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.smartawning.R
import com.example.smartawning.databinding.ActivityMainBinding
import com.example.smartawning.utils.Variables
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AppActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var controller: NavController
    private lateinit var snackbar : Snackbar
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<CoordinatorLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        controller = navHostFragment.navController

        setSupportActionBar(binding?.toolbar)
        setupActionBarWithNavController(controller)
        supportActionBar?.title = resources.getString(R.string.toolbar_title)

        setupObservers()
        setupOnBackCallBack()

        setContentView(binding?.root)
    }

    private fun setupOnBackCallBack()   {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                val isNavigated = controller.navigateUp()

                if(!isNavigated)    {
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (controller.currentBackStackEntry != null)
                    controller.navigateUp()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        snackbar = Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_INDEFINITE)

        Variables.liveNetworkConnection.observe(this, { connection ->
            if (!connection) {
                snackbar.setText(R.string.connectionLost)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                snackbar.dismiss()
            }
        })
    }
}