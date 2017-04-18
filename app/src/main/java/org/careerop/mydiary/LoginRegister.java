package org.careerop.mydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class LoginRegister extends AppCompatActivity {

    private AppCompatButton btnLogin;
    private EditText etUsername, etPassword;
    private String username = "JuyelRana";
    private String password = "@%Juyel58";
    private String userNameFromEt, passWordFromEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin = (AppCompatButton) findViewById(R.id.buttonLogin);
        etUsername = (EditText) findViewById(R.id.editTextUsername);
        etPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();

            }
        });
    }

    private void LoginUser() {

        userNameFromEt = etUsername.getText().toString();
        passWordFromEt = etPassword.getText().toString();

        if (userNameFromEt.equals(username) && passWordFromEt.equals(password)) {
            SharedPreferences sharedPreferences = getSharedPreferences("My Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", etUsername.getText().toString().trim());
            editor.putString("password", etPassword.getText().toString().trim());

            editor.commit();

            Toast.makeText(LoginRegister.this, "Login Success!", Toast.LENGTH_SHORT).show();

            //After 1.5 sec goto main activity
            try {
                sleep(1500);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(LoginRegister.this, "Login Failed!", Toast.LENGTH_SHORT).show();
        }
    }

}
