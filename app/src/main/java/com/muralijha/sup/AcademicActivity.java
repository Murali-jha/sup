package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import static com.muralijha.sup.profile.profileCollege;

public class AcademicActivity extends AppCompatActivity {
    private ImageView iv1,iv2,iv3;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        iv1=findViewById(R.id.iva1);
        iv2=findViewById(R.id.iva2);
        iv3=findViewById(R.id.iva3);
        toolbar=findViewById(R.id.ToolbarAcademic);
        initToolbar();
        setup();
    }
    private void setup(){
        String item3 = profileCollege.getText().toString();
        if(item3.equals("BML Munjal University")){
            iv1.setImageResource(R.drawable.ac1);
            iv2.setImageResource(R.drawable.ac2);
            iv3.setImageResource(R.drawable.ac3);
        }else if(item3.equals("GITAM University")){
            iv1.setImageResource(R.drawable.calender1);
            iv2.setImageResource(R.drawable.calender);
            iv3.setImageResource(R.drawable.calender1);
        }
        else{
            Toast.makeText(AcademicActivity.this,"Some error",Toast.LENGTH_SHORT).show();
        }
    }


    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Academic Calender");
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
