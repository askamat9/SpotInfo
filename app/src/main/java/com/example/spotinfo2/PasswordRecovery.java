package com.example.spotinfo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordRecovery extends AppCompatActivity {

    private Button resetPass;
    private FirebaseAuth mAuth;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        resetPass = (Button) findViewById(R.id.resetButton);
        email = (EditText) findViewById(R.id.enterEmail);
        mAuth = FirebaseAuth.getInstance();

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PasswordRecovery.this, "Password reset email sent", Toast.LENGTH_LONG).show();
                            backToLogin();
                        } else {
                            Toast.makeText(PasswordRecovery.this, "ERROR - Password reset email not sent", Toast.LENGTH_LONG).show();
                            email.requestFocus();
                            email.setError("Verify whether you have entered a valid email address");
                        }
                    }
                });
            }
        });
    }

    public void backToLogin(){
        startActivity(new Intent(this, MainActivity.class));
    }
}
