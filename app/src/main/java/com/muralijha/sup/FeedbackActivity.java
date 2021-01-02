package com.muralijha.sup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class FeedbackActivity extends AppCompatActivity {
    private EditText namedata,emaildata,messagedata;
    private Button send,details;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        namedata=findViewById(R.id.etFeedbackUsername);
        emaildata=findViewById(R.id.etFeedbackEmail);
        messagedata=findViewById(R.id.etFeedback);
        send=findViewById(R.id.SendFeedback);
toolbar=(Toolbar)findViewById(R.id.ToolbarFeedback);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/html");
                i.putExtra(Intent.EXTRA_EMAIL,new String("murali.jha.19cse@bmu.edu.in "));
                i.putExtra(Intent.EXTRA_SUBJECT,"Feedback");
                i.putExtra(Intent.EXTRA_TEXT,"Name - " + namedata.getText() +"\n\nEmail - "+ emaildata.getText() +"\n\nMessage - " + messagedata.getText());
                try{
                    startActivity(Intent.createChooser(i,"Please Select Email"));

                }catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(FeedbackActivity.this, "There are no email clients", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(FeedbackActivity.this,"Send Email to murali.jha.19cse@bmu.edu.in :) ",Toast.LENGTH_LONG).show();

            }
        });


        initToolbar();
    }


    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Feedback");
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
