package com.jpp.roundedimages;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainScreen extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        if (savedInstanceState == null) {
            ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
            MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
            vpPager.setAdapter(adapter);
            vpPager.setPageTransformer(true, new ZoomOutPageTransformer());
            vpPager.setOnPageChangeListener(new OnPageChangeListener() {


                // This method will be invoked when a new page becomes selected.
                @Override
                public void onPageSelected(int position) {
                    IFragmentListener fragment =
                            (IFragmentListener) getSupportFragmentManager().findFragmentByTag(
                                    "android:switcher:" + R.id.vpPager + ":" + position);
                    if (fragment != null) {
                        fragment.onShow();
                    }
                }

                // This method will be invoked when the current page is scrolled
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // Code goes here
                }

                // Called when the scroll state changes:
                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING,
                // SCROLL_STATE_SETTLING
                @Override
                public void onPageScrollStateChanged(int state) {
                    // Code goes here
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
