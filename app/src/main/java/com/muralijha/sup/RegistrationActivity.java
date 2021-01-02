package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userName,userEmail,userPassword,userSemester;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private CircleImageView userProfilePic;
    String name,email,semester,password,school,year,section,college;
    public static Spinner spinner1;
    public static Spinner spinner2;
    public static Spinner spinner3;
    public static Spinner spinner4;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData()!=null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                userProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,R.layout.custom_spinner,
                getResources().getStringArray(R.array.School)
        );
        adapter.setDropDownViewResource(R.layout.custom_layout_dropdown);
        spinner1.setAdapter(adapter);

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this,R.layout.custom_spinner,
                getResources().getStringArray(R.array.Sem)
        );
        adapter1.setDropDownViewResource(R.layout.custom_layout_dropdown);
        spinner2.setAdapter(adapter1);


        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this,R.layout.custom_spinner,
                getResources().getStringArray(R.array.user)
        );
        adapter2.setDropDownViewResource(R.layout.custom_layout_dropdown);
        spinner3.setAdapter(adapter2);


        spinner4 = findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(
                this,R.layout.custom_spinner,
                getResources().getStringArray(R.array.University)
        );
        adapter3.setDropDownViewResource(R.layout.custom_layout_dropdown);
        spinner4.setAdapter(adapter3);

        setupUIViews();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();

        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*"); //for any kind of doc pickup use application/* and for audio use audio/*
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE);

            }
        });





        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    progressDialog.setMessage("Registration in Process");
                    progressDialog.show();
                    String user_email = userEmail.getText().toString().trim();
                    String user_password= userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.cancel();
                              sendEmailVerification();
                            }
                            else
                            {
                                progressDialog.cancel();
                                Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });
    }
    private void setupUIViews(){
        userName=(EditText)findViewById(R.id.etUsername);
        userEmail=(EditText)findViewById(R.id.etUserEmail);
        userPassword=(EditText)findViewById(R.id.etUserPassword);
        regButton=(Button) findViewById(R.id.btnRegister);
        userLogin=(TextView) findViewById(R.id.tvUserLogin);
        userSemester=(EditText)findViewById(R.id.etSemester);
        userProfilePic=(CircleImageView)findViewById(R.id.ivProfile);
        progressDialog =new ProgressDialog(this);


    }
    private Boolean validate(){
        Boolean result = false;
        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        semester = userSemester.getText().toString();
        school=spinner1.getSelectedItem().toString();
        year=spinner2.getSelectedItem().toString();
        section=spinner3.getSelectedItem().toString();
        college=spinner4.getSelectedItem().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()|| semester.isEmpty() || imagePath==null ||school==null || year==null ||section==null||college==null ){
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }else if(password.length()<6){
            Toast.makeText(RegistrationActivity.this,"Password should contain minimum of six values",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserdata();
                        Toast.makeText(RegistrationActivity.this,"Successfully Registered,Verification mail has been sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                    }else{
                        Toast.makeText(RegistrationActivity.this,"Something wrong!Verification mail has not been sent",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserdata(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic"); //user id/Images/Profile Pic
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this,"Uploading Failed:(",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RegistrationActivity.this,"Upload Successful!",Toast.LENGTH_SHORT).show();
            }
        });
        UserProfile userProfile = new UserProfile(semester,email,name,school,year,section,college);
        myRef.setValue(userProfile);
    }
}
