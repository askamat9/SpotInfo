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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText pass;
    private EditText confPass;
    private Button signMeUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.enterEmail);
        pass = findViewById(R.id.enterPass);
        confPass = findViewById(R.id.enterPass2);
        signMeUp = findViewById(R.id.button);
        signMeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();
                String userPass2 = confPass.getText().toString();

                if(userEmail.isEmpty()){
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    pass.requestFocus();
                    confPass.requestFocus();
                }else if(!isValidEmail(userEmail)){
                    email.setError("Please enter a valid email");
                    email.requestFocus();
                    pass.requestFocus();
                    confPass.requestFocus();
                }else if(userPass.isEmpty() || userPass2.isEmpty()){
                    if(userPass.isEmpty()){
                        pass.setError("Please enter a password");
                    } else {
                        confPass.setError("Please enter a password");
                    }
                    pass.requestFocus();
                    confPass.requestFocus();
                }else if(!userPass.equals(userPass2)){
                    confPass.setError("Passwords do not match");
                    pass.requestFocus();
                    confPass.requestFocus();
                }else {
                    createAccount(userEmail, userPass);
                }
            }
        });
    }

    public void createAccount(String username, String password){

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, "Sign Up attempt unsuccessful, please try again", Toast.LENGTH_SHORT).show();

                } else{
                    Toast.makeText(SignUpActivity.this, "Sign Up attempt successful, please log in", Toast.LENGTH_SHORT).show();
                    backToLogin();
                }
            }
        });
    }

    public void backToLogin(){
        startActivity(new Intent(this, MainActivity.class));
    }

    public static boolean isValidEmail(String target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
