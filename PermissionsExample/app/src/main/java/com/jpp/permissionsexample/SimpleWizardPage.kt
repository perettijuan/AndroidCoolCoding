package com.jpp.permissionsexample

import android.support.v4.app.Fragment

class SimpleWizardPage : WelcomeWizardPage {
    override val fragmentPage: Fragment
        get() = SimpleFragment.newInstance()

}