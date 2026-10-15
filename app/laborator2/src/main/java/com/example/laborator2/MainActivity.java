package com.example.laborator2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("seminar", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("seminar", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("seminar", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("seminar", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("seminar", "onDestroy");
    }


}