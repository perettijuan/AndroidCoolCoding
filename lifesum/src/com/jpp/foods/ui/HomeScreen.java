package com.jpp.foods.ui;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.jpp.foods.R;
import com.jpp.foods.ui.fragment.FoodsFragment;

/**
 * Activity that represents the Home screen. This Activity will show the list of
 * stored Foods (if any) and will let the user search for Foods stored in the
 * web server.
 * 
 * <br>
 * Implements {@link OnQueryTextListener} in order to be able to show the
 * results of the queries. <br>
 * Implements {@link OnActionExpandListener} in order to be able to change the
 * layout view when the user closes the search view.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class HomeScreen extends Activity implements SearchView.OnQueryTextListener, OnActionExpandListener {


    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_screen_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        setupSearchView(menu.findItem(R.id.menu_search));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        FoodsFragment fr = (FoodsFragment) getFragmentManager().findFragmentByTag(FoodsFragment.TAG);
        if (fr == null) {
            fr = FoodsFragment.newInstance();
            android.app.FragmentTransaction tr = getFragmentManager().beginTransaction();
            tr.add(R.id.home_fragment_container, fr, FoodsFragment.TAG);
            tr.commit();
        }
        fr.setFoodToSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Do nothing on purpose
        return false;
    }

    /**
     * Sets up the search menu item to indicate that this Activity is the one
     * that is going to take care of the search functionality.
     */
    private void setupSearchView(MenuItem searchItem) {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            mSearchView.setSearchableInfo(info);
        }

        searchItem.setOnActionExpandListener(this);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint(getString(R.string.search_hint));
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        // Do nothing on purpose.
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag(FoodsFragment.TAG)).commit();
        return true;
    }

}
