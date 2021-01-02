package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfile extends AppCompatActivity {
private EditText newUserName,newUserEmail,newUserSemester;
private Spinner newUserSchool,newUserYear,newUserSection,newUserCollege;
private Button save;
    private Toolbar toolbar;
private FirebaseAuth firebaseAuth;
private FirebaseDatabase firebaseDatabase;
private FirebaseStorage firebaseStorage;
private CircleImageView updateProfilePic;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data.getData()!=null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                updateProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        newUserName=findViewById(R.id.etNameUpdate);
        newUserEmail=findViewById(R.id.etEmailUpdate);
        newUserSemester=findViewById(R.id.etSemesterUpdate);
        newUserSchool = findViewById(R.id.spinnerSchool);
        ArrayAdapter<CharSequence> set = ArrayAdapter.createFromResource(this,R.array.School,R.layout.support_simple_spinner_dropdown_item);
        newUserSchool.setAdapter(set);

        newUserYear = findViewById(R.id.spinnerYear);
        ArrayAdapter <CharSequence> set1 = ArrayAdapter.createFromResource(this,R.array.Sem,R.layout.support_simple_spinner_dropdown_item);
        newUserYear.setAdapter(set1);

        newUserSection = findViewById(R.id.spinnerSection);
        ArrayAdapter <CharSequence> set2 = ArrayAdapter.createFromResource(this,R.array.user,R.layout.support_simple_spinner_dropdown_item);
        newUserSection.setAdapter(set2);

        newUserCollege = findViewById(R.id.spinnerCollege);
        ArrayAdapter <CharSequence> set3 = ArrayAdapter.createFromResource(this,R.array.University,R.layout.support_simple_spinner_dropdown_item);
        newUserCollege.setAdapter(set3);


        save=findViewById(R.id.btnSave);
        updateProfilePic= findViewById(R.id.ivProfileUpdate);

        toolbar=(Toolbar)findViewById(R.id.ToolbarUpdate);

        initToolbar();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        final DatabaseReference databaseReference =firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
               newUserName.setText(userProfile.getUserName());
               newUserSemester.setText(userProfile.getUserSemester());
               newUserEmail.setText(userProfile.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });


        final StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(updateProfilePic);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String semester = newUserSemester.getText().toString();
                String email = newUserEmail.getText().toString();
                String name = newUserName.getText().toString();
                String school = newUserSchool.getSelectedItem().toString();
                String year = newUserYear.getSelectedItem().toString();
                String section = newUserSection.getSelectedItem().toString();
                String college = newUserCollege.getSelectedItem().toString();


                UserProfile userProfile = new UserProfile(semester,email,name,school,year,section,college);

                databaseReference.setValue(userProfile);
                StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic"); //user id/Images/Profile Pic
                UploadTask uploadTask = imageReference.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this,"Uploading Failed:(",Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UpdateProfile.this,"Upload Successful!",Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(UpdateProfile.this,"Updated Successfully",Toast.LENGTH_SHORT).show();


                finish();
                startActivity(new Intent(UpdateProfile.this,profile.class));




            }
        });

        updateProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*"); //for any kind of doc pickup use application/* and for audio use audio/*
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE);

            }
        });


    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Credentials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
