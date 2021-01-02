package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileYear;


public class SubjectDetails extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setUIViews();
        initToolbar();
        setup();
    }

    private void setUIViews() {
        toolbar = (Toolbar) findViewById(R.id.ToolbarSubjectDetails);
        listView = (ListView) findViewById(R.id.lvSubjectDetails);
        ;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
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
            Toast.makeText(SubjectDetails.this,"Please Select Proper Option in The Profile",Toast.LENGTH_SHORT).show();
        }
    }


    private void setupListViewSecA() {
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF, null);
        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.titles);
        if (subject_selected.equalsIgnoreCase("Subject1")) {
            syllabus = getResources().getStringArray(R.array.Subject1);
        } else if (subject_selected.equalsIgnoreCase("Subject2")) {
            syllabus = getResources().getStringArray(R.array.Subject2);
        } else if (subject_selected.equalsIgnoreCase("Subject3")) {
            syllabus = getResources().getStringArray(R.array.Subject3);
        } else if (subject_selected.equalsIgnoreCase("Subject4")) {
            syllabus = getResources().getStringArray(R.array.Subject4);
        } else if (subject_selected.equalsIgnoreCase("Subject5")) {
            syllabus = getResources().getStringArray(R.array.Subject5);
        } else {
            syllabus = getResources().getStringArray(R.array.Subject6);
        }
        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this, titles, syllabus);
        listView.setAdapter(subjectDetailsAdapter);
    }

    public class SubjectDetailsAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus) {
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);

            }
            title = convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = convertView.findViewById(R.id.tvSyllabus);
            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);
            return convertView;
        }

    }




    private void setupListViewSecB() {
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF, null);
        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.titlesSec);
        if (subject_selected.equalsIgnoreCase("Subject1Sec")) {
            syllabus = getResources().getStringArray(R.array.Subject1Sec);
        } else if (subject_selected.equalsIgnoreCase("Subject2Sec")) {
            syllabus = getResources().getStringArray(R.array.Subject2Sec);
        } else if (subject_selected.equalsIgnoreCase("Subject3Sec")) {
            syllabus = getResources().getStringArray(R.array.Subject3Sec);
        } else if (subject_selected.equalsIgnoreCase("Subject4Sec")) {
            syllabus = getResources().getStringArray(R.array.Subject4Sec);
        } else if (subject_selected.equalsIgnoreCase("Subject5Sec")) {
            syllabus = getResources().getStringArray(R.array.Subject5Sec);
        } else {
            syllabus = getResources().getStringArray(R.array.Subject6Sec);
        }
        SubjectDetailsAdapterSec subjectDetailsAdapter = new SubjectDetailsAdapterSec(this, titles, syllabus);
        listView.setAdapter(subjectDetailsAdapter);
    }

    public class SubjectDetailsAdapterSec extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

        public SubjectDetailsAdapterSec(Context context, String[] title, String[] syllabus) {
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);

            }
            title = convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = convertView.findViewById(R.id.tvSyllabus);
            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);
            return convertView;
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

