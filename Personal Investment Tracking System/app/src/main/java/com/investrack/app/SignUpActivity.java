package com.investrack.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        TextView signin = findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validateForm() {
        EditText usernameET = findViewById(R.id.username);
        EditText emailET = findViewById(R.id.email);
        EditText passwordET = findViewById(R.id.password);
        EditText confirmpasswordET = findViewById(R.id.repassword);

        String username = usernameET.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(this, "Username shouldn't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        String email = emailET.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "Email shouldn't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        String password = passwordET.getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "Password shouldn't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        String confirmpassword = confirmpasswordET.getText().toString();
        if (!confirmpassword.equals(password)) {
            Toast.makeText(this, "Password and confirm password are not same", Toast.LENGTH_SHORT).show();
            return false;
        }

        SharedPreferences sharedPref = getSharedPreferences("prefs_file", Context.MODE_PRIVATE);
        String prefUsername = sharedPref.getString("username", "");

        if (username.equals(prefUsername)) {
            Toast.makeText(this, "Username is already registered. Please sign-" +
                    "in.", Toast.LENGTH_SHORT).show();
            return false;
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();

        return true;
    }
}