package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    private Toolbar toolbar;
    private CircleImageView profilePic;
    public static TextView profileName,profileSemester,profileEmail,profileSchool,profileYear,profileSection,profileCollege;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        profilePic = findViewById(R.id.ivProfilePic);
        profileName = findViewById(R.id.tvProfileName);
        profileSemester = findViewById(R.id.tvProfileSemester);
        profileEmail = findViewById(R.id.tvProfileEmail);
        profileSchool = findViewById(R.id.tvProfileSchool);
        profileYear = findViewById(R.id.tvProfileYear);
        profileSection = findViewById(R.id.tvProfileSection);
        profileCollege=findViewById(R.id.tvProfileCollege);
        progressDialog = new ProgressDialog(this);




        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();



        progressDialog.setMessage("Updating Profile");
        progressDialog.show();


        DatabaseReference databaseReference =firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(profilePic);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileName.setText(userProfile.getUserName());
                profileSemester.setText(userProfile.getUserSemester());
                profileEmail.setText(userProfile.getUserEmail());
                profileSchool.setText(userProfile.getUserSchool());
                profileYear.setText(userProfile.getUserYear());
                profileSection.setText(userProfile.getUserSection());
                profileCollege.setText(userProfile.getUserCollege());

                progressDialog.cancel();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profile.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });



        toolbar=(Toolbar)findViewById(R.id.ToolbarProfile);
        initToolbar();
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }

        }
        switch(item.getItemId()) {
            case R.id.Edit: {
                startActivity(new Intent(profile.this, UpdateProfile.class));
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }



}
