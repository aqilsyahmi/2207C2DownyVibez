package com.rafapps.simplenotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class EntryPointActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_entry_point);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean passwordSet = preferences.getBoolean("password_set", false);
        Log.d("EntryPointActivity", "passwordSet: " + passwordSet);

        Intent intent;
        if (passwordSet) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, NotesListActivity.class);
        }
        startActivity(intent);
        finish();
    }
}



