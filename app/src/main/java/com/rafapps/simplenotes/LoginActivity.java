package com.rafapps.simplenotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Disable Screenshot
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        password = preferences.getString("password", "");

        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPassword = passwordEditText.getText().toString();
                String decryptedStoredPassword = null;

                try {
                    if (!password.isEmpty()) {
                        decryptedStoredPassword = EncryptionUtils.decrypt(password);
                    }
                } catch (Exception e) {
                    Log.e("Encryption Error", "Error decrypting password: " + e.getMessage());
                }

                if (decryptedStoredPassword != null && enteredPassword.equals(decryptedStoredPassword)) {
                    Log.d("DecryptSuccess", "Decrypt success! ");
                    Intent intent = new Intent(LoginActivity.this, NotesListActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect password, please try again", Toast.LENGTH_SHORT).show();
                    passwordEditText.setText("");
                }
            }
        });
    }
}
