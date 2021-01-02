package com.muralijha.sup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static com.muralijha.sup.profile.profileCollege;
import static com.muralijha.sup.profile.profileEmail;
import static com.muralijha.sup.profile.profileName;
import static com.muralijha.sup.profile.profileSchool;
import static com.muralijha.sup.profile.profileSection;
import static com.muralijha.sup.profile.profileSemester;
import static com.muralijha.sup.profile.profileYear;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private long backPressedTime;
    private Toast backToast;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
private Toolbar toolbar;
private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        toolbar=(Toolbar)findViewById(R.id.ToolbarMain);
        setSupportActionBar(toolbar);
        dl = (DrawerLayout) findViewById(R.id.drawer);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.drawerOpen, R.string.drawerClose);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView)findViewById(R.id.NavigationView);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.profile) {
                    Intent intent = new Intent(MainActivity.this,profile.class);
                    startActivity(intent);
                }
                else if(id==R.id.about) {
                    Intent intent = new Intent(MainActivity.this,AboutusActivity.class);
                    startActivity(intent);
                }
                else if(id==R.id.developer) {
                    Intent intent = new Intent(MainActivity.this,Developer.class);
                    startActivity(intent);
                }
                else if(id==R.id.feedback) {
                    Intent intent = new Intent(MainActivity.this,FeedbackActivity.class);
                    startActivity(intent);
                }
                else if(id==R.id.Invite) {
                    Intent myIntent = new Intent(Intent.ACTION_SEND);
                    myIntent.setType("text/plain");
                    String shareBody= "Your body here";
                    String shareSub = "Your Subject here";
                    myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                    myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                    startActivity(Intent.createChooser(myIntent,"Invite Friend using"));
                }
                else if(id==R.id.rate) {
                    Toast.makeText(MainActivity.this, "Feature not added yet", Toast.LENGTH_SHORT).show();
                }
                else if(id==R.id.exit) {
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(MainActivity.this);
                    alert1.setMessage("DO U REALLY WANT TO EXIT!")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = alert1.create();
                    alert.setTitle("Alert!");
                    alert.show();
                }
                else {
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(MainActivity.this);
                    alert1.setMessage("DO U REALLY WANT TO LOGOUT!")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    firebaseAuth.signOut();
                                    finish();
                                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                    Toast.makeText(MainActivity.this, "Logged out succesfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = alert1.create();
                    alert.setTitle("Alert!");
                    alert.show();


                }
                return true;
            }
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
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
                        return true;

                    case R.id.About:
                        startActivity(new Intent(getApplicationContext(),
                                About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;

            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Welcome Back !!";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        setupUIViews();
        initToolbar();
        setupListView();
        autoUpdate();

    }
private void setupUIViews(){
        toolbar=(Toolbar)findViewById(R.id.ToolbarMain);

        listview = (ListView)findViewById(R.id.lvMain);
}
private void initToolbar(){
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("SUP");

}

    private void setupListView(){
        String[] title=getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray((R.array.Description));
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,title,description);
        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:{
                        Intent intent= new Intent(MainActivity.this,WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        Intent intent= new Intent(MainActivity.this,SubjectActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Intent intent= new Intent(MainActivity.this,FacultyActivity.class);
                        startActivity(intent);
                        break;
                    }case 3:{
                        Intent intent= new Intent(MainActivity.this,AcademicActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 4:{
                        Toast.makeText(
                                MainActivity.this,"Feature not added yet",Toast.LENGTH_SHORT
                        ).show();

                        Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }



    private void autoUpdate(){
       startActivity(new Intent(MainActivity.this,profile.class));
    }

    public class SimpleAdapter extends BaseAdapter{
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title,description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;
        public SimpleAdapter(Context context,String[] title,String[] description){
            mContext=context;
            titleArray=title;
            descriptionArray=description;
            layoutInflater=LayoutInflater.from(context);
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
            if(convertView==null){
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item,null);

            }
            title= convertView.findViewById(R.id.tvMain);
            description= convertView.findViewById(R.id.tvDescription);
            imageView= convertView.findViewById(R.id.ivMain);
            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);
            if(titleArray[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.timetable1);
            }else if(titleArray[position].equalsIgnoreCase("Syllabus")){
                imageView.setImageResource(R.drawable.book);
            }else if(titleArray[position].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.calender);
            }else if(titleArray[position].equalsIgnoreCase("Calender")){
                imageView.setImageResource(R.drawable.timetable);

            }else{
                imageView.setImageResource(R.drawable.settings);
            }
return convertView;
        }
    }


   /* private  void  fetchdata(){
        Intent intent = new Intent(MainActivity.this,profile.class);
        startActivity(intent);
    }*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* switch (item.getItemId()){
            case R.id.fetchData:{
                fetchdata();
            }
        }*/
        if(abdt.onOptionsItemSelected(item)){
            return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast=Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }


}
