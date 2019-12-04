package com.shadhin.androidarchitecture.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shadhin.androidarchitecture.R;
import com.shadhin.androidarchitecture.mvc.CountriesController;

import java.util.ArrayList;
import java.util.List;

public class MVPActivity extends AppCompatActivity implements CountriesPresenter.View{
    private List<String> listValues = new ArrayList<>();
    private ArrayList<String> vals = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView lvlist;
    private Button btnRetry;
    private ProgressBar pb;
    private CountriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle("MVP");
        presenter = new CountriesPresenter(this);
        init();
        setAdapter();
        //setStaticValue();
        buttonAction();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVPActivity.class);
    }
    private void init() {
        lvlist = findViewById(R.id.lvlist);
        btnRetry = findViewById(R.id.btnRetry);
        pb = findViewById(R.id.pb);

        lvlist.setVisibility(View.GONE);
        btnRetry.setVisibility(View.GONE);
    }
    private void setAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.row_country_name, R.id.txtlistText, listValues);
        lvlist.setAdapter(adapter);
        lvlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVPActivity.this, "You Clicked + " + listValues.get(position), Toast.LENGTH_LONG).show();
            }
        });

    }
    private void buttonAction() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                presenter.refresh();
            }
        });

    }

    @Override
    public void setValue(List<String> value) {
        pb.setVisibility(View.GONE);
        lvlist.setVisibility(View.VISIBLE);
        listValues.clear();
        listValues.addAll(value);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        pb.setVisibility(View.GONE);
        btnRetry.setVisibility(View.VISIBLE);
        Toast.makeText(MVPActivity.this, "Unable to Connect ,try Again later ", Toast.LENGTH_LONG).show();
    }
}
