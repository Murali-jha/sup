package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileEmail;
import static com.muralijha.sup.profile.profileName;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileSemester;
import static com.muralijha.sup.profile.profileYear;

public class LoginActivity extends AppCompatActivity {
    private EditText Name;
    public static ImageView image;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter=5;
    private TextView userRegistration;
    private FirebaseDatabase firebaseDatabase;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
RelativeLayout rellay1,rellay2;
Handler handler  = new Handler();
Runnable runnable =new Runnable() {
    @Override
    public void run() {
        rellay1.setVisibility(View.VISIBLE);
        rellay2.setVisibility(View.VISIBLE);

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        image=findViewById(R.id.ivp);

        rellay1=(RelativeLayout)findViewById(R.id.rellay1);
        rellay2=(RelativeLayout)findViewById(R.id.rellay2);
        firebaseDatabase= FirebaseDatabase.getInstance();

        handler.postDelayed(runnable,2000);



        Name=findViewById(R.id.etName);
        Password=findViewById(R.id.etPassword);
        Info=findViewById(R.id.tvinfo);
        Login=findViewById(R.id.btnlogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);
        forgotPassword=findViewById(R.id.tvForgotPassword);

        Info.setText("Attempts Remaining : 5");

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,splashscreen.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
            }
        });




    }

    private void validate(String userName,String userPassword){

        progressDialog.setMessage("Directing you to MainPage");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    checkEmailVerification();
                }else{

                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Attempts Remaining : "+ counter);
                    progressDialog.dismiss();
                    if(counter==0){
                        Login.setEnabled(false);
                    }
                }
            }
        });

    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag){
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }
        else{
            Toast.makeText(LoginActivity.this,"Please verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }


}
