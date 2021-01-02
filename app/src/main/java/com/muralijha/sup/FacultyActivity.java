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

import com.muralijha.sup.Utils.LetterImageView;


import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileYear;


public class FacultyActivity extends AppCompatActivity {
    private ListView listView;
    private Toolbar toolbar;
    public static SharedPreferences sharedPreferences;
   public static String SEL_FACULTY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);


        setUIViews();
        initToolbar();
        setup();
    }
    private void setUIViews(){
        toolbar=(Toolbar)findViewById(R.id.ToolbarFaculty);
        listView=(ListView)findViewById(R.id.lvFaculty);;
        sharedPreferences=getSharedPreferences("myFaculty",MODE_PRIVATE);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setup(){

        String item = profileSchool.getText().toString();
        String item1 = profileYear.getText().toString();
        String item2 = profileSection.getText().toString();
        String item3 = profileCollege.getText().toString();
        if(item3.equals("BML Munjal University")&&item.equals("BTech,Cs/Cse")&&item1.equals("1")&&item2.equals("Section A")) {
            setupListViewSecB();
        }
        else if(item3.equals("BML Munjal University")&&item.equals("BTech,Cs/Cse")&&item1.equals("1")&&item2.equals("Section B")){
            setupListViewSecA();
        }else{
            Toast.makeText(FacultyActivity.this,"Some error",Toast.LENGTH_SHORT).show();
        }
    }



    private void setupListViewSecA(){
        String[] faculty_names=getResources().getStringArray(R.array.faculty_name_SecA);
        FacultyAdapterSecA adapterSecA =new FacultyAdapterSecA(this,R.layout.faculty_single_item,faculty_names);
        listView.setAdapter(adapterSecA);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,0).apply();
                        break;}
                    case 1:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,1).apply();
                        break;}
                    case 2:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,2).apply();
                        break;}
                    case 3:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,3).apply();
                        break;}
                    case 4:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,4).apply();
                        break;}
                    case 5:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,5).apply();
                        break;}
                    case 6:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,6).apply();
                        break;}
                    default:break;
                }
            }
        });
    }

    public class FacultyAdapterSecA extends ArrayAdapter {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] faculty =new String[]{};

        public FacultyAdapterSecA(Context context, int resource, String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.faculty=objects;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView=layoutInflater.inflate(resource,null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterFaculty);
                holder.tvFaculty=(TextView)convertView.findViewById(R.id.tvFacultyName);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(faculty[position].charAt(0));
            holder.tvFaculty.setText(faculty[position]);

            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvFaculty;
        }
    }


    private void setupListViewSecB(){
        String[] faculty_names=getResources().getStringArray(R.array.faculty_name_SecB);
        FacultyAdapterSecB adapter =new FacultyAdapterSecB(this,R.layout.faculty_single_item,faculty_names);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,0).apply();
                        break;}
                    case 1:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,1).apply();
                        break;}
                    case 2:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,2).apply();
                        break;}
                    case 3:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,3).apply();
                        break;}
                    case 4:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,4).apply();
                        break;}
                    case 5:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,5).apply();
                        break;}
                    case 6:{startActivity(new Intent(FacultyActivity.this,FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY,6).apply();
                        break;}
                    default:break;
                }
            }
        });
    }

    public class FacultyAdapterSecB extends ArrayAdapter {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] faculty =new String[]{};

        public FacultyAdapterSecB(Context context, int resource, String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.faculty=objects;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView=layoutInflater.inflate(resource,null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterFaculty);
                holder.tvFaculty=(TextView)convertView.findViewById(R.id.tvFacultyName);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(faculty[position].charAt(0));
            holder.tvFaculty.setText(faculty[position]);

            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvFaculty;
        }
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
