package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.muralijha.sup.Utils.LetterImageView;

public class Dashboard extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar=(Toolbar)findViewById(R.id.ToolbarDashboard);
        initToolbar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Toast.makeText(Dashboard.this,"Feature not added yet",Toast.LENGTH_SHORT).show();
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashboard:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.About:
                        startActivity(new Intent(getApplicationContext(),
                                About.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;

            }
        });}

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Classroom");
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
