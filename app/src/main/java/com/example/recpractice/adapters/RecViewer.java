package com.example.recpractice.adapters;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recpractice.DatabaseHelper;
import com.example.recpractice.MainActivity;
import com.example.recpractice.R;
import com.example.recpractice.SecondActivity;
import com.example.recpractice.model.Movie;

import java.util.List;
import java.util.logging.Logger;


//Hocu da napravim reusable recyclerview zato pravim svoju klasu koja extenduje klasicni recyclerview
public class RecViewer extends RecyclerView {
    private static RecViewer INSTANCE;
    private static final String TAG = "RecViewer";

    


//ovde imam konstruktor koji prima parametar kontekst,nisam siguran da li mi on treba ali pretpostavljam da time vezuje rec sa aktivnoscu koja ga pokrece
    private RecViewer(@NonNull Context context,List<Movie>  listFromDb) {
        super(context);

        MovieAdapter movieAdapter = new MovieAdapter(listFromDb, context);

        this.setAdapter(movieAdapter);
        //movieAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);

        this.setLayoutManager(layoutManager);
        this.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        //LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );


        //Activity activity=(Activity)context;

        LinearLayout linearLayout=findViewById(R.id.main_layout);
        //this.setLayoutParams(params);
        linearLayout.addView(this);
        //activity.addContentView(R.layout.my_fr_layout,params);

    }

//krenuo sam da pravim sve to kao singleton da bi bio samo jedan,reusable,samo da mu setujem drugi adapter u zavisnosti koja aktivnost ga pokrece,
    //ono sto me buni je -
    //ako moj konstruktor pravi objekat i instancira parametre- da li onda ovaj dole findViewById pregazi moj objekat i nalepi novi prazan bez konteksta?
    //Sta radi findViewById? Pravi objekat i lepi ga na referencu?
    public static RecViewer getINSTANCE(Context myContext,List<Movie> listFromDb){
        if(INSTANCE==null){

            INSTANCE=new RecViewer(myContext, listFromDb);
            return INSTANCE;
        }else{
            return INSTANCE;

        }
    }




}
