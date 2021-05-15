package com.example.third_assignment_template;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner deletespinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        this.deletespinner = findViewById(R.id.deletespinner);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));

        ArrayAdapter listAdapter = new ArrayAdapter <>  (getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        this.deletespinner.setAdapter(listAdapter);

    }

    public void onDeleteNoteClick (View view) {
        String selected = this.deletespinner.getSelectedItem().toString();

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newStrSet = new HashSet<String>();
        newStrSet.remove(selected);
        newStrSet.addAll(oldSet);

        spEd.putStringSet("notes",newStrSet);
        spEd.apply();

        finish();
    }
}
