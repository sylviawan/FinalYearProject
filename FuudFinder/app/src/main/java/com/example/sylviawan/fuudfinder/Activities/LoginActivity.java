package com.example.sylviawan.fuudfinder.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sylviawan.fuudfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail, userPass;
    private Button logBtn, toRegBtn;

    private ProgressBar logProcessBar;

    private FirebaseAuth mAuth;
    private Intent HomeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.loginEmail);
        userPass = findViewById(R.id.loginPass);
        logBtn = findViewById(R.id.loginBtn);
        toRegBtn = findViewById(R.id.btnToReg);
        logProcessBar = findViewById(R.id.logProgressBar);
        logProcessBar.setVisibility(View.INVISIBLE);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logProcessBar.setVisibility(View.VISIBLE);
                logBtn.setVisibility(View.INVISIBLE);

                final String Email = userEmail.getText().toString();
                final String Password = userPass.getText().toString();

                if ( Email.isEmpty() || Password.isEmpty()) {

                    displayMessage("Login details incorrect!");
                }
                else {
                    logIn(Email, Password);
                }

            }
        });

        toRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regActivity);
                finish();
            }
        });

    }

    private void logIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    logProcessBar.setVisibility(View.INVISIBLE);
                    logBtn.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else {
                    displayMessage(task.getException().getMessage());
                }
            }
        });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void displayMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }


}
