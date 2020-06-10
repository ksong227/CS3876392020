package com.example.menuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        Button buttonSMS = (Button) findViewById(R.id.buttonSMS);
        buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+Uri.encode("1234567890")));
                intent.putExtra("sms_body","Hello Kevin Song!");
                startActivity(intent);
            }
        });

        Button buttonPhone = (Button) findViewById(R.id.buttonPhone);
        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1234567890"));
                startActivity(intent);
            }
        });

        Button buttonWeb = (Button) findViewById(R.id.buttonWeb);
        buttonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://github.com"));
                startActivity(intent);
            }
        });

        Button buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "geo:35.6237555,139.7756717";
                Uri geo = Uri.parse(geoUri);
                Intent intent = new Intent(Intent.ACTION_VIEW, geo);
                startActivity(intent);
            }
        });

        Button buttonShare = (Button) findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"This is a Test");
                intent.putExtra(Intent.EXTRA_TEXT,"Share the love!");
                startActivity(Intent.createChooser(intent,"Share the love!"));
            }
        });

        Button buttonNewAct = (Button) findViewById(R.id.buttonNewAct);
        buttonNewAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActNew();
            }
        });
    }

    //Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuHelp:
                startActHelp();
                return true;
            case R.id.menuSettings:
                Toast.makeText(this, "Nothing to see here! _(ゝヽε:)ﾉ", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //StartActivities
    public void startActHelp()
    {
        startActivity(new Intent(this, HelpActivity.class));
    }

    public void startActNew()
    {
        startActivity(new Intent(this, NewActivity.class));
    }
}