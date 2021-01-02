package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.muralijha.sup.RegistrationActivity.spinner1;
import static com.muralijha.sup.RegistrationActivity.spinner2;
import static com.muralijha.sup.RegistrationActivity.spinner3;
import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileYear;

public class FacultyDetails extends AppCompatActivity {
    private CircleImageView facultyImage;
    private Toolbar toolbar;
    private TextView facultyName,phoneNumber,email,place,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);
        setUIViews();
        initToolbar();
        setup();
    }
    private void setUIViews(){
        toolbar=(Toolbar)findViewById(R.id.ToolbarFacultyDetails);
        facultyImage= (CircleImageView)findViewById(R.id.ivProfilePic);
        facultyName=(TextView)findViewById(R.id.tvFacultySelName);
        phoneNumber =(TextView)findViewById(R.id.tvPhoneNumber);
        email =(TextView)findViewById(R.id.tvProfileEmail);
        place =(TextView)findViewById(R.id.tvPlace);
        description=(TextView)findViewById(R.id.tvDescription);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void setup(){

        String item = profileSchool.getText().toString();
        String item1 = profileYear.getText().toString();
        String item2 = profileSection.getText().toString();
        String item3 = profileCollege.getText().toString();
        if(item3.equals("BML Munjal University")&&item.equals("BTech,Cs/Cse")&&item1.equals("1")&&item2.equals("Section A")) {
            setupDetailsSecB();
        }
        else if(item3.equals("BML Munjal University")&&item.equals("BTech,Cs/Cse")&&item1.equals("1")&&item2.equals("Section B")){
            setupDetailsSecA();
        }else{
            Toast.makeText(FacultyDetails.this,"Some error",Toast.LENGTH_SHORT).show();
        }
    }
    private void setupDetailsSecA(){
        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY,0);
        String[] facultyNames=getResources().getStringArray(R.array.faculty_name_SecA);
        int[] facultyImages = new int[]{R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh};
        int[] facultyArray = new int[]{R.array.faculty1,R.array.faculty2,R.array.faculty3,R.array.faculty4,R.array.faculty5,R.array.faculty6,R.array.faculty7};
        String[] facultyDetails=getResources().getStringArray(facultyArray[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        place.setText(facultyDetails[2]);
        description.setText(facultyDetails[3]);
        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);
    }

    private void setupDetailsSecB(){
        int faculty_pos = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY,0);
        String[] facultyNames=getResources().getStringArray(R.array.faculty_name_SecB);
        int[] facultyImages = new int[]{R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh,R.drawable.lord_ganesh};
        int[] facultyArray = new int[]{R.array.faculty1Sec,R.array.faculty2Sec,R.array.faculty3Sec,R.array.faculty4Sec,R.array.faculty5Sec,R.array.faculty6Sec,R.array.faculty7Sec};
        String[] facultyDetails=getResources().getStringArray(facultyArray[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        place.setText(facultyDetails[2]);
        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);
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
