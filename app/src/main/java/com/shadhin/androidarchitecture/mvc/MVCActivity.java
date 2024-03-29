package com.shadhin.androidarchitecture.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shadhin.androidarchitecture.R;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class MVCActivity extends AppCompatActivity {
    private List<String> listValues = new ArrayList<>();
    private ArrayList<String> vals = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView lvlist;
    private Button btnRetry;
    private ProgressBar pb;
    private CountriesController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        controller = new CountriesController(this);
        setTitle("MVC");
        init();
        setAdapter();
        //setStaticValue();
        buttonAction();
    }

    private void buttonAction() {
        btnRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                controller.refresh();
            }
        });

    }

    private void setStaticValue() {
        listValues.clear();
        vals.add("USA");
        vals.add("India");
        vals.add("Pakisthan");
        vals.add("Bangladesh");
        vals.add("Japan");
        listValues.addAll(vals);
        adapter.notifyDataSetChanged();
    }

    public void setValue(List<String> value) {

        pb.setVisibility(View.GONE);
        lvlist.setVisibility(View.VISIBLE);
        listValues.clear();
        listValues.addAll(value);
        adapter.notifyDataSetChanged();
    }

    private void setAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.row_country_name, R.id.txtlistText, listValues);
        lvlist.setAdapter(adapter);
        lvlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVCActivity.this, "You Clicked + " + listValues.get(position), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void init() {
        lvlist = findViewById(R.id.lvlist);
        btnRetry = findViewById(R.id.btnRetry);
        pb = findViewById(R.id.pb);

        lvlist.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVCActivity.class);
    }

    public void onError() {
        pb.setVisibility(View.GONE);
        btnRetry.setVisibility(View.VISIBLE);
        Toast.makeText(MVCActivity.this, "Unable to Connect ,try Again later ", Toast.LENGTH_LONG).show();
    }

}
