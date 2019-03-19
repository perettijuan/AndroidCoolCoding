package com.jpp.permissionsexample

import android.support.v4.app.Fragment

class PermissionPage : WelcomeWizardPage {
    override val fragmentPage: Fragment
        get() = PermissionsFragment.newInstance()
}