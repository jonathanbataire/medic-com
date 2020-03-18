package com.example.medcom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private Button hospital,fire,police;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        hospital = findViewById(R.id.hospital);
        police = findViewById(R.id.police);
        fire = findViewById(R.id.fire);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                myIntent.putExtra("message", "hospital");
                MainActivity.this.startActivity(myIntent);
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                myIntent.putExtra("message", "fire");
                MainActivity.this.startActivity(myIntent);
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                myIntent.putExtra("message", "police");
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
