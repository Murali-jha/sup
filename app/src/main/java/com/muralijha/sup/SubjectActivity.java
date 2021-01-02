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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.muralijha.sup.Utils.LetterImageView;

import javax.security.auth.Subject;

import static com.muralijha.sup.RegistrationActivity.spinner1;
import static com.muralijha.sup.RegistrationActivity.spinner2;
import static com.muralijha.sup.RegistrationActivity.spinner3;
import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileYear;

public class SubjectActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    private String [] subjects;
    public static SharedPreferences subjectPreferences;
    public static String SUB_PREF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        setUIViews();
        initToolbar();
        setup();
    }
    private void setUIViews(){
        toolbar=(Toolbar)findViewById(R.id.ToolbarSubject);
        listView=(ListView)findViewById(R.id.lvSubject);
        subjectPreferences = getSharedPreferences("Subjects",MODE_PRIVATE);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subject");
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
            Toast.makeText(SubjectActivity.this,"Some error",Toast.LENGTH_SHORT).show();
        }

    }


    private void setupListViewSecA(){
        subjects=getResources().getStringArray(R.array.SubjectsSecA);
        SubjectAdapter subjectAdapter = new SubjectAdapter(this,R.layout.subject_single_item,subjects);
        listView.setAdapter(subjectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject1").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject2").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject3").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject4").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject5").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 5:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject6").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 6:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject7").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 7:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject8").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }
    public class SubjectAdapter extends ArrayAdapter {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] Subjects =new String[]{};
        private LetterImageView letterImageView;

        public SubjectAdapter(Context context, int resource, String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.Subjects=objects;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView=layoutInflater.inflate(resource,null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterSubject);
                holder.tvSubject=(TextView)convertView.findViewById(R.id.tvSubject);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(Subjects[position].charAt(0));
            holder.tvSubject.setText(Subjects[position]);
            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvSubject;
        }
    }






    private void setupListViewSecB(){
        subjects=getResources().getStringArray(R.array.SubjectsSecB);
        SubjectAdapterSecB subjectAdapterSecB = new SubjectAdapterSecB(this,R.layout.subject_single_item,subjects);
        listView.setAdapter(subjectAdapterSecB);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject1Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject2Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject3Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 3:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject4Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject5Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 5:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject6Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 6:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject7Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 7:{
                        subjectPreferences.edit().putString(SUB_PREF,"Subject8Sec").apply();
                        Intent intent = new Intent(SubjectActivity.this,SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }
    public class SubjectAdapterSecB extends ArrayAdapter {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] Subjects =new String[]{};
        private LetterImageView letterImageView;

        public SubjectAdapterSecB(Context context, int resource, String[] objects) {
            super(context, resource,objects);
            this.resource=resource;
            this.Subjects=objects;
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                holder = new ViewHolder();
                convertView=layoutInflater.inflate(resource,null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterSubject);
                holder.tvSubject=(TextView)convertView.findViewById(R.id.tvSubject);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(Subjects[position].charAt(0));
            holder.tvSubject.setText(Subjects[position]);
            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvSubject;
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
