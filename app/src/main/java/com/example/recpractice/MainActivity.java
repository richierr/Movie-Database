package com.example.recpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.example.recpractice.fragments.FavsListFragment;
import com.example.recpractice.fragments.ListFragment;
import com.example.recpractice.fragments.MainMoviesListFragment;
import com.example.recpractice.fragments.SettingsFragment;
import com.example.recpractice.model.Movie;
import com.example.recpractice.utils.AppConstants;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ListFragment.OnMovieItemClickInteface {

    private Toolbar myToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        SharedPreferences sharedPreferences=getSharedPreferences("com.example.recpractice_preferences",MODE_PRIVATE);
        boolean showSplash=sharedPreferences.getBoolean("show_splash",true);
        Toast.makeText(this, " Show splash "+showSplash, Toast.LENGTH_SHORT).show();
        if(showSplash){

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            ListFragment listFragment = new MainMoviesListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);
            fragmentTransaction.add(R.id.placeholder, listFragment);
            fragmentTransaction.commit();

        }
//        setUpToolbar();

        setUpDrawer();

    }

    private void setUpDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUpToolbar();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        Drawable toolbarDrawable = ContextCompat.getDrawable(this, R.drawable.ic_baseline_menu_24);
        actionBarDrawerToggle.setToolbarNavigationClickListener(v -> {
            if (mDrawerLayout.isDrawerOpen(navigationView)) {
                mDrawerLayout.closeDrawers();
            } else {
                mDrawerLayout.openDrawer(navigationView);
            }

        });
        myToolbar.setNavigationIcon(toolbarDrawable);
        actionBarDrawerToggle.syncState();

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                selectDrawerItem(item);
//                return true;
//            }
//        });
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_favs:
                ListFragment listFragment = new FavsListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.replace(R.id.placeholder, listFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                break;


        }
    }

    private void setUpToolbar() {
        myToolbar = findViewById(R.id.toolbar_reusable);
        setSupportActionBar(myToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        //getMenuInflater().inflate(R.menu.my_navigation_items,menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
//                ListFragment listFragment=new FavsListFragment();
//                FragmentManager fragmentManager=getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.setReorderingAllowed(true);
//                fragmentTransaction.replace(R.id.placeholder,listFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            //Todo: implemntiraj ovo
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }


    @Override
    public void onMovieItemClick(int index) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mDrawerLayout.isDrawerOpen(navigationView)) {
            mDrawerLayout.closeDrawer(navigationView);
        }
        if (item.getItemId() == R.id.show_favs) {
            navigateToScreen(AppConstants.SCREEN_FAV_MOVIES);
            return true;
        } else if (item.getItemId() == R.id.settings){
            navigateToScreen(AppConstants.SCREEN_SETTINGS);
            return true;
        }else if(item.getItemId()==R.id.main_list){
            navigateToScreen(AppConstants.SCREEN_MAIN);
        }
        return false;
    }

    private void navigateToScreen(int screenFavMovies) {
        Fragment fragment;
        switch (screenFavMovies) {
            case AppConstants.SCREEN_FAV_MOVIES:
                fragment = new FavsListFragment();
                break;
            case AppConstants.SCREEN_SETTINGS:
                fragment = new SettingsFragment();
                Toast.makeText(this, "KLIKNUO NA PREF", Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.SCREEN_MAIN:
                fragment=new MainMoviesListFragment();
                break;
            default:
                fragment=new MainMoviesListFragment();


        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.replace(R.id.placeholder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}