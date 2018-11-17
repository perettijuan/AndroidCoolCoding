package com.jpp.navigationcomponents.ui


import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
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
        lockAppBar()

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


    fun lockAppBar() {
        appBarLayout.setExpanded(false, false)
        appBarLayout.isActivated = false
        val lp = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        lp.height = resources.getDimension(R.dimen.actionBarSize).toInt()
        collapsingImage.visibility = View.GONE
        collapsing_toolbar.isTitleEnabled = false
    }

    /**
     * Unlock the AppBarLayout with an animation.
     * The idea is that the Fragments that are shown by this Activity, takes the responsibility
     * of enabling/disabling the scrolling behavior of the CollapsingToolbarLayout that is
     * hosted in this Activity.
     */
    fun unlockApp() {
        appBarLayout.setExpanded(true, false)
        appBarLayout.isActivated = true


        val valueAnimator = ValueAnimator.ofInt(appBarLayout.measuredHeight, resources.getDimension(R.dimen.toolbar_expand_height).toInt())
        valueAnimator.addUpdateListener {
            val newHeight = it.animatedValue as Int
            val lp = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
            lp.height = newHeight
            appBarLayout.layoutParams = lp
        }
        valueAnimator.duration = 300
        valueAnimator.start()

        collapsingImage.visibility = View.VISIBLE
        collapsing_toolbar.isTitleEnabled = true
    }
}