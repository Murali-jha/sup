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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import static com.muralijha.sup.RegistrationActivity.spinner1;
import static com.muralijha.sup.RegistrationActivity.spinner2;
import static com.muralijha.sup.RegistrationActivity.spinner3;
import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileYear;


public class DayDetail extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listview;
    public static String[] MondaySecA;
    public static String[] TuesdaySecA;
    public static String[] WednesdaySecA;
    public static String[] ThursdaySecA;
    public static String[] FridaySecA;
    public static String[] SaturdaySecA;
    public static String[] SundaySecA;
    public static String[] Time1SecA;
    public static String[] Time2SecA;
    public static String[] Time3SecA;
    public static String[] Time4SecA;
    public static String[] Time5SecA;
    public static String[] Time6SecA;
    public static String[] Time7SecA;
    private String[]  PreferredDaySecA;
    private String[] PreferredTimeSecA;

    public static String[] MondaySecB;
    public static String[] TuesdaySecB;
    public static String[] WednesdaySecB;
    public static String[] ThursdaySecB;
    public static String[] FridaySecB;
    public static String[] SaturdaySecB;
    public static String[] SundaySecB;
    public static String[] Time1SecB;
    public static String[] Time2SecB;
    public static String[] Time3SecB;
    public static String[] Time4SecB;
    public static String[] Time5SecB;
    public static String[] Time6SecB;
    public static String[] Time7SecB;
    private String[] PreferredDaySecB;
    private String[] PreferredTimeSecB;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);
        listview=(ListView)findViewById(R.id.lvDayDetail);


        setupUIViews();
        initToolbar();
        setup();


    }
    private void setupUIViews(){
        listview=(ListView)findViewById(R.id.lvDayDetail);
        toolbar=(Toolbar)findViewById(R.id.ToolbarDayDetail);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY,null));
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
            Toast.makeText(DayDetail.this,"Some error",Toast.LENGTH_SHORT).show();
        }
    }


//FOR SECTION A


    private void setupListViewSecA() {
            MondaySecA = getResources().getStringArray(R.array.MondaySecA);
            TuesdaySecA = getResources().getStringArray(R.array.TuesdaySecA);
            WednesdaySecA = getResources().getStringArray(R.array.WednesdaySecA);
            ThursdaySecA = getResources().getStringArray(R.array.ThursdaySecA);
            FridaySecA = getResources().getStringArray(R.array.FridaySecA);
            SaturdaySecA = getResources().getStringArray(R.array.SaturdaySecA);
            SundaySecA = getResources().getStringArray(R.array.SundaySecA);


            Time1SecA = getResources().getStringArray(R.array.time1SecA);
            Time2SecA = getResources().getStringArray(R.array.time2SecA);
            Time3SecA = getResources().getStringArray(R.array.time3SecA);
            Time4SecA = getResources().getStringArray(R.array.time4SecA);
            Time5SecA = getResources().getStringArray(R.array.time5SecA);
            Time6SecA = getResources().getStringArray(R.array.time6SecA);
            Time7SecA = getResources().getStringArray(R.array.time7SecA);

            String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);
            if (selected_day.equalsIgnoreCase("Monday")) {
                PreferredDaySecA = MondaySecA;
                PreferredTimeSecA = Time1SecA;
            } else if (selected_day.equalsIgnoreCase("Tuesday")) {
                PreferredDaySecA = TuesdaySecA;
                PreferredTimeSecA = Time2SecA;
            } else if (selected_day.equalsIgnoreCase("Wednesday")) {
                PreferredDaySecA = WednesdaySecA;
                PreferredTimeSecA = Time3SecA;
            } else if (selected_day.equalsIgnoreCase("Thursday")) {
                PreferredDaySecA = ThursdaySecA;
                PreferredTimeSecA = Time4SecA;
            } else if (selected_day.equalsIgnoreCase("Friday")) {
                PreferredDaySecA = FridaySecA;
                PreferredTimeSecA = Time5SecA;
            } else if (selected_day.equalsIgnoreCase("Saturday")) {
                PreferredDaySecA = SaturdaySecA;
                PreferredTimeSecA = Time6SecA;
            } else {
                PreferredDaySecA = SundaySecA;
                PreferredTimeSecA = Time7SecA;
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(DayDetail.this, PreferredDaySecA, PreferredTimeSecA);
            listview.setAdapter(simpleAdapter);
    }


        public class SimpleAdapter extends BaseAdapter {
            private Context mContext;
            private LayoutInflater layoutInflater;
            private TextView subject,time;
            private String[] subjectArray;
            private String[] timeArray;
            public SimpleAdapter(Context context,String[] subjectArray,String[] timeArray){
                mContext=context;
                this.subjectArray=subjectArray;
                this.timeArray=timeArray;
                layoutInflater=LayoutInflater.from(context);
            }
            @Override
            public int getCount() {
                return subjectArray.length;
            }

            @Override
            public Object getItem(int position) {
                return subjectArray[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView = layoutInflater.inflate(R.layout.day_detail_single_item,null);

                }
                subject= convertView.findViewById(R.id.tvSubjectDayDetails);
                time= convertView.findViewById(R.id.tvTimeDayDetail);
                subject.setText(subjectArray[position]);
                time.setText(timeArray[position]);

                return convertView;
            }
        }

 //SECTION A END

 //FOR SECTION B

    private void setupListViewSecB() {
        MondaySecB = getResources().getStringArray(R.array.MondaySecB);
        TuesdaySecB = getResources().getStringArray(R.array.TuesdaySecB);
        WednesdaySecB = getResources().getStringArray(R.array.WednesdaySecB);
        ThursdaySecB = getResources().getStringArray(R.array.ThursdaySecB);
        FridaySecB = getResources().getStringArray(R.array.FridaySecB);
        SaturdaySecB = getResources().getStringArray(R.array.SaturdaySecB);
        SundaySecB = getResources().getStringArray(R.array.SundaySecB);


        Time1SecB = getResources().getStringArray(R.array.time1SecB);
        Time2SecB = getResources().getStringArray(R.array.time2SecB);
        Time3SecB = getResources().getStringArray(R.array.time3SecB);
        Time4SecB = getResources().getStringArray(R.array.time4SecB);
        Time5SecB = getResources().getStringArray(R.array.time5SecB);
        Time6SecB = getResources().getStringArray(R.array.time6SecB);
        Time7SecB = getResources().getStringArray(R.array.time7SecB);

        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);
        if (selected_day.equalsIgnoreCase("Monday")) {
            PreferredDaySecB = MondaySecB;
            PreferredTimeSecB = Time1SecB;
        } else if (selected_day.equalsIgnoreCase("Tuesday")) {
            PreferredDaySecB = TuesdaySecB;
            PreferredTimeSecB = Time2SecB;
        } else if (selected_day.equalsIgnoreCase("Wednesday")) {
            PreferredDaySecB = WednesdaySecB;
            PreferredTimeSecB = Time3SecB;
        } else if (selected_day.equalsIgnoreCase("Thursday")) {
            PreferredDaySecB = ThursdaySecB;
            PreferredTimeSecB = Time4SecB;
        } else if (selected_day.equalsIgnoreCase("Friday")) {
            PreferredDaySecB = FridaySecB;
            PreferredTimeSecB = Time5SecB;
        } else if (selected_day.equalsIgnoreCase("Saturday")) {
            PreferredDaySecB = SaturdaySecB;
            PreferredTimeSecB = Time6SecB;
        } else {
            PreferredDaySecB = SundaySecB;
            PreferredTimeSecB = Time7SecB;
        }
        SimpleAdapter1 simpleAdapter1 = new SimpleAdapter1(DayDetail.this, PreferredDaySecB, PreferredTimeSecB);
        listview.setAdapter(simpleAdapter1);


}


    public class SimpleAdapter1 extends BaseAdapter {
        private Context mContext1;
        private LayoutInflater layoutInflater1;
        private TextView subject1,time1;
        private String[] subjectArray1;
        private String[] timeArray1;
        public SimpleAdapter1(Context context,String[] subjectArray1,String[] timeArray1){
            mContext1=context;
            this.subjectArray1=subjectArray1;
            this.timeArray1=timeArray1;
            layoutInflater1=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return subjectArray1.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray1[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = layoutInflater1.inflate(R.layout.day_detail_single_item,null);

            }
            subject1= convertView.findViewById(R.id.tvSubjectDayDetails);
            time1= convertView.findViewById(R.id.tvTimeDayDetail);
            subject1.setText(subjectArray1[position]);
            time1.setText(timeArray1[position]);

            return convertView;
        }
    }


//SECTION B END




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
