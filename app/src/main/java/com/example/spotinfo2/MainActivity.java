package com.example.spotinfo2;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button signUp, login;
    private TextView forgotPass;
    private FirebaseAuth mAuth;
    private EditText email, pass;
    private static final String TAG = "EmailPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = (Button) findViewById(R.id.button1);
        login = (Button) findViewById(R.id.button2);
        forgotPass = (TextView) findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.editText4);
        pass = (EditText) findViewById(R.id.enterPass2);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPasswordRecoveryActivity();
            }
        });
    }

    private void openSignUpActivity(){
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void openHomeActivity(){
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void openPasswordRecoveryActivity(){
        Intent intent = new Intent(this, PasswordRecovery.class);
        startActivity(intent);
    }

    private void login(){
        String username = email.getText().toString();
        String password = pass.getText().toString();
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   Log.d(TAG, "signInWithEmail:success");
                    openHomeActivity();
                } else{
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    email.setError("Email or password is incorrect");
                    pass.setError("Email or password is incorrect");
                    email.requestFocus();
                    pass.requestFocus();
                }
            }
        });
    }
}
