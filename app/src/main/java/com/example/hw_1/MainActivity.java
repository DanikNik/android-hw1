package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hw_1.fragments.TableFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            TableFragment fragment = new TableFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        }
    }
}
