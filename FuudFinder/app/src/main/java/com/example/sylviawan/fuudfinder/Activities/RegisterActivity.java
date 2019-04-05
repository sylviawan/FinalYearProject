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

import com.example.sylviawan.fuudfinder.Class.User;
import com.example.sylviawan.fuudfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    DatabaseReference dataUsers;

    private EditText userName, userEmail, userPass, userPass2;
    private Button submitBtn;
    private ProgressBar progressBar;

    private FirebaseStorage storage;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.regName);
        userEmail = findViewById(R.id.regEmail);
        userPass = findViewById(R.id.regPass);
        userPass2 = findViewById(R.id.regPass2);
        submitBtn = findViewById(R.id.regSubmit);
        progressBar = findViewById(R.id.regProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        dataUsers = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitBtn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                final String name = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPass.getText().toString();
                final String password2 = userPass2.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty() || !password.equals(password2)) {

                    displayMessage("Please check all fields");
                    submitBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else {
                    CreateNewUser(name, email, password);

                    // Firebase user
                    String userId = dataUsers.push().getKey();
                    User user = new User(userId,name,email);
                    dataUsers.child(userId).setValue(user);
                }
            }
        });
    }


    private void CreateNewUser(final String name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            displayMessage("Registration completed!");

                            updateUser(name, mAuth.getCurrentUser());
                        }
                        else {
                            displayMessage("Registration not complete!" + task.getException().getMessage());
                            submitBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    // Display message to user
    private void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG).show();

    }

    private void updateUser(String name, FirebaseUser currentUser) {

        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();

        currentUser.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override

            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    displayMessage("Register Success!");
                    updateUI();
                }
            }
        });
    }

    private void writeNewUser(String userId, String name, String email) {


    }

    //Intents to the home activity
    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(), Home.class);
        startActivity(homeActivity);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            updateUI();
        }
    }
}