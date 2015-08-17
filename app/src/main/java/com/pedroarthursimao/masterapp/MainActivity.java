package com.pedroarthursimao.masterapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pedroarthursimao.masterapp.butterknife.ButterKnifeFragment;
import com.pedroarthursimao.masterapp.dagger2.Dagger2Fragment;
import com.pedroarthursimao.masterapp.maps.MapsFragment;
import com.pedroarthursimao.masterapp.otto.OttoFragment;
import com.pedroarthursimao.masterapp.retrofit.RetrofitFragment;
import com.pedroarthursimao.masterapp.sqlite.SQLiteFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String STATE_SELECTED_MENU_ID = "STATE_SELECTED_MENU_ID";
    private static final String SP_SYSTEM = "SP_SYSTEM";
    private static final String DRAWER_INTRODUCE = "DRAWER_INTRODUCE";

    private static int currentMenuId = 0;

    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpNavView();
        if (savedInstanceState != null) {
            currentMenuId = savedInstanceState.getInt(STATE_SELECTED_MENU_ID, R.id.mi_home);
        } else {
            currentMenuId = R.id.mi_home;
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.push_down_in, R.anim.push_down_out)
                    .replace(R.id.container, new HomeFragment())
                    .commit();
        }
        navigationView.getMenu().findItem(currentMenuId).setChecked(true);
        introduceDrawer();
    }

    private void setUpNavView() {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void introduceDrawer() {
        if (!getSharedPreferences(SP_SYSTEM, MODE_PRIVATE).getBoolean(DRAWER_INTRODUCE, false)) {
            drawerLayout.openDrawer(GravityCompat.START);
            getSharedPreferences(SP_SYSTEM, MODE_PRIVATE).edit().putBoolean(DRAWER_INTRODUCE, true).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (currentMenuId == menuItem.getItemId()) {
            return true;
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = getFragmentByMenu(menuItem.getItemId());
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.push_down_in, R.anim.push_down_out)
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        Snackbar.make(getCurrentFocus(), getString(R.string.drawer_selection_empty), Snackbar.LENGTH_LONG).show();
        return false;
    }

    private Fragment getFragmentByMenu(int menuId) {
        Fragment fragment = null;
        switch (menuId) {
            case R.id.mi_home:
                fragment = new HomeFragment();
                break;
            case R.id.mi_sqlite:
                fragment = new SQLiteFragment();
                break;
            case R.id.mi_butterknife:
                fragment = new ButterKnifeFragment();
                break;
            case R.id.mi_otto:
                fragment = new OttoFragment();
                break;
            case R.id.mi_dagger2:
                fragment = new Dagger2Fragment();
                break;
            case R.id.mi_retrofit:
                fragment = new RetrofitFragment();
                break;
            case R.id.mi_maps:
                fragment = new MapsFragment();
                break;
        }
        currentMenuId = (fragment != null) ? menuId : currentMenuId;
        return fragment;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_MENU_ID, currentMenuId);
        super.onSaveInstanceState(outState);
    }
}
