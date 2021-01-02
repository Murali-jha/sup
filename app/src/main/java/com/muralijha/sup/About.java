package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar=(androidx.appcompat.widget.Toolbar)findViewById(R.id.ToolbarAbout);
        initToolbar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Toast.makeText(About.this,"Feature not added yet",Toast.LENGTH_SHORT).show();
        bottomNavigationView.setSelectedItemId(R.id.About);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                switch(menuItem.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),
                                Dashboard.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.About:
                        return true;
                }
                return false;

            }
        });
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Assignments");
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
