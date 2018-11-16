package com.jpp.navigationcomponents.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import androidx.core.view.GravityCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.*
import com.jpp.navigationcomponents.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        // ensures that the menu items in the Nav Drawer stay in sync with the navigation graph
        return navigateUp(drawerLayout, findNavController(this, R.id.nav_host_fragment))
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupNavigation() {
        val navController = findNavController(this, R.id.nav_host_fragment)

        /*
         * Ensures that the title in the action bar will automatically be updated when the
         * destination changes (assuming that android:label values are set up).
         * In addition, the Up button will be displayed when you are on a non-root destination
         * and the hamburger icon will be displayed when on the root destination.
         */
        setupActionBarWithNavController(this, navController, drawerLayout)

        // Handle nav drawer item clicks
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        /*
         * Ensures that the selected item in the NavigationView will automatically be
         * updated when the destination changes.
         */
        setupWithNavController(navigationView, navController)
    }
}