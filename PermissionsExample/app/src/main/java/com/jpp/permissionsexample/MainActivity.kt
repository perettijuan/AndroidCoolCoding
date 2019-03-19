package com.jpp.permissionsexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showWizard(listOf(SimpleWizardPage(), PermissionPage()), supportFragmentManager)
    }

    fun moveToPage(page: Int) {
        welcome_wizard_view_pager.currentItem = page
    }

    private fun showWizard(pages: List<WelcomeWizardPage>, fragmentManager: FragmentManager) {
        welcome_wizard_view_pager.adapter = WizardViewPagerAdapter(fragmentManager, pages)
        welcome_wizard_view_pager.visibility = View.VISIBLE
    }

    private class WizardViewPagerAdapter(fm: FragmentManager, private val mPages: List<WelcomeWizardPage>) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return mPages[position].fragmentPage
        }

        override fun getCount(): Int {
            return mPages.size
        }
    }
}
