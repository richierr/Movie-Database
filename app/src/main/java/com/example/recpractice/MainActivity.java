package com.example.recpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;


import com.example.recpractice.fragments.FavsListFragment;
import com.example.recpractice.fragments.ListFragment;
import com.example.recpractice.fragments.MainMoviesListFragment;
import com.example.recpractice.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListFragment.OnMovieItemClickInteface {

    private Toolbar myToolbar;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(savedInstanceState==null){

            ListFragment listFragment=new MainMoviesListFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);
            fragmentTransaction.add(R.id.placeholder,listFragment);
            fragmentTransaction.commit();
        }
        setUpToolbar();


    }

    private void setUpToolbar(){
        myToolbar = findViewById(R.id.toolbar_reusable);
        setSupportActionBar(myToolbar);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                ListFragment listFragment=new FavsListFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.setReorderingAllowed(true);
                fragmentTransaction.replace(R.id.placeholder,listFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

        }
        return true;
    }




    @Override
    public void onMovieItemClick(int index) {

    }
}