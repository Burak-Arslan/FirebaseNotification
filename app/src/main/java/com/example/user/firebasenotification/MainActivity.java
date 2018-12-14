package com.example.user.firebasenotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String firebaseID = new FirebaseInstanceService().GetID();
        Toast.makeText(getApplicationContext(), firebaseID, Toast.LENGTH_SHORT).show();
    }
}
