package com.example.recpractice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recpractice.adapters.MovieAdapter;
import com.example.recpractice.model.Favs;
import com.example.recpractice.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private Dao<Favs,Integer> favsIntegerDao;
    private DatabaseHelper databaseHelper;
    private List<Movie> listFromDatabase;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myToolbar = findViewById(R.id.toolbar_reusable);
        setSupportActionBar(myToolbar);



        databaseHelper= DatabaseHelper.getINSTANCE(this);
        favsIntegerDao=databaseHelper.getFavsDao();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second_ac,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.back_icon_second_act){
            onBackPressed();
        }
        return true;
    }
}
