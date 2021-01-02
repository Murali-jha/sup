package com.muralijha.sup;

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

public class PasswordActivity extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordEmail=findViewById(R.id.etPasswordEmail);
        resetPassword = findViewById(R.id.btnResetPassword);
        firebaseAuth=FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(PasswordActivity.this,"Please enter your Email",Toast.LENGTH_SHORT).show();

                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(PasswordActivity.this,"Password Reset Email Sent!",Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(PasswordActivity.this,LoginActivity.class));
                           }else{
                               Toast.makeText(PasswordActivity.this,"Error in sending Password Reset Email!",Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
            }
        });
    }
}
