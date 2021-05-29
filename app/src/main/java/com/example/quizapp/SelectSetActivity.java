package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.File;

// SOLID
// SRP - klasa jest odpowiedzialna jedynie za wybieranie zestawu

public class SelectSetActivity extends AppCompatActivity {

    public String[] sets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_set);

        loadSets();
    }

    public void loadSets()
    {
        File catalog = new File(getFilesDir(), "Sets");
        if(!catalog.exists()){
            catalog.mkdir();
        }

        sets = new String[catalog.listFiles().length];
        for(int i = 0; i < catalog.listFiles().length; i++)
        {
            sets[i] = catalog.listFiles()[i].toString().substring(catalog.listFiles()[i].toString().lastIndexOf('/')+1,
                    catalog.listFiles()[i].toString().lastIndexOf('.'));
            //System.out.println(sets[i]);

            Button bt = new Button(this);
            bt.setText(sets[i]);
            bt.setTag(sets[i]);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogFragment ptdf = new PickTypeDialogFragment(v.getTag().toString());

//                    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                    // To make it fullscreen, use the 'content' root view as the container
//                    // for the fragment, which is always the root view for the activity
//                    transaction.add(android.R.id.content, ptdf)
//                            .addToBackStack(null).commit();
                    ptdf.show(getSupportFragmentManager(), "lol");

                    //Toast.makeText(SelectSetActivity.this, "les gooo",
                            //Toast.LENGTH_SHORT).show();
                }
            });
            ((LinearLayout)findViewById(R.id.linearSets)).addView(bt);
        }
    }

}
