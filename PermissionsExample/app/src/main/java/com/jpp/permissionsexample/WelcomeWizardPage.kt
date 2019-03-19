package com.jpp.permissionsexample

import android.support.v4.app.Fragment

/**
 * Defines a Page that will be shown in the GDPR wizard.
 */
interface WelcomeWizardPage {

    /**
     * Creates the Fragment that will be shown as the page's content.
     * It doesn't keeps a reference to the Fragment, just creates a new
     * instance each time that is requested.
     */
    val fragmentPage: Fragment
}
