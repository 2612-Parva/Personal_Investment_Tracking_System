package com.investrack.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        TextView signup = findViewById(R.id.txtregister);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validate() {
        EditText usernameET = findViewById(R.id.username);
        EditText passwordET = findViewById(R.id.password);

        String username = usernameET.getText().toString();
        if (username.isEmpty()) {
            Toast.makeText(this, "Username is not registered", Toast.LENGTH_SHORT).show();
            return false;
        }
        String password = passwordET.getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "Password is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }

        SharedPreferences sharedPref = getSharedPreferences("prefs_file", Context.MODE_PRIVATE);
        String prefUsername = sharedPref.getString("username", "");
        String prefPassword = sharedPref.getString("password", "");

        if (!username.equals(prefUsername) || !password.equals(prefPassword)) {
            Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}