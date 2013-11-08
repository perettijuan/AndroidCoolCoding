package com.jpp.animations.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jpp.animations.Animations;
import com.jpp.animations.R;
import com.jpp.animations.ui.fragment.SimpleScaleAnimationFragment;
import com.jpp.animations.ui.fragment.SliderMenuFragment;

public class HomeActivity extends ActionBarActivity implements ISldierMenuSelectionListener {


    private static final String TITLE_KEY = "title_key";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private SliderMenuFragment mSliderMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getCharSequence(TITLE_KEY);
            setTitle(mTitle);
        } else {
            mTitle = mDrawerTitle = getTitle();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // creates call to onPrepareOptionsMenu()
                supportInvalidateOptionsMenu();

                // hide keyword
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow((IBinder) getCurrentFocus().getWindowToken(), 0);
            }
        };

        if (savedInstanceState == null) {
            mSliderMenuFragment = SliderMenuFragment.newInstance(this);
            getSupportFragmentManager().beginTransaction().add(R.id.left_drawer, mSliderMenuFragment).commit();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mSliderMenuFragment = (SliderMenuFragment) getSupportFragmentManager().findFragmentByTag(SliderMenuFragment.TAG);
        if (mSliderMenuFragment != null) {
            mSliderMenuFragment.changeListener(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence(TITLE_KEY, mTitle);
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAimationSelected(Animations anim) {
        switch (anim) {
        case SIMPLAE_SCALE:
            SimpleScaleAnimationFragment fr = SimpleScaleAnimationFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fr).commit();
            break;

        default:
            break;
        }
        mDrawerLayout.closeDrawers();
    
    }

}
