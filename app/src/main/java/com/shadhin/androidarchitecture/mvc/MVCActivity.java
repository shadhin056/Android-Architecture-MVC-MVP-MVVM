package com.shadhin.androidarchitecture.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shadhin.androidarchitecture.R;

public class MVCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle("MVC");
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVCActivity.class);
    }
}
