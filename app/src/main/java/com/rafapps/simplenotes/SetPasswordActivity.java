package com.rafapps.simplenotes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetPasswordActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        final EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button savePasswordButton = findViewById(R.id.setPasswordButton);

        savePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                savePassword(password);
                Toast.makeText(SetPasswordActivity.this, "Password set successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void savePassword(String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", password);
        editor.putBoolean("password_set", true); // add this line to set password_set to true
        editor.apply();
        Log.d("SavePassword", "password_set: " + preferences.getBoolean("password_set", false));
    }
}



