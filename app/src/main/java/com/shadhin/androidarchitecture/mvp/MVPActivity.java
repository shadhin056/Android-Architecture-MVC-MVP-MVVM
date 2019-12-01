package com.shadhin.androidarchitecture.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shadhin.androidarchitecture.R;
import com.shadhin.androidarchitecture.mvc.MVCActivity;

public class MVPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle("MVP");
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVPActivity.class);
    }
}
