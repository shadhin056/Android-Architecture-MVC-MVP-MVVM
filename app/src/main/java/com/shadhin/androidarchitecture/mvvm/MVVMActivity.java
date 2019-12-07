package com.shadhin.androidarchitecture.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

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
import com.shadhin.androidarchitecture.mvp.CountriesPresenter;
import com.shadhin.androidarchitecture.mvp.MVPActivity;

import java.util.ArrayList;
import java.util.List;

public class MVVMActivity extends AppCompatActivity {

    private List<String> listValues = new ArrayList<>();
    private ArrayList<String> vals = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView lvlist;
    private Button btnRetry;
    private ProgressBar pb;
    private CountriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("MVVM");

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);
        init();
        setAdapter();
        //setStaticValue();
        buttonAction();
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getCountries().observe(this,country->{
        if(country!=null){
            pb.setVisibility(View.GONE);
            lvlist.setVisibility(View.VISIBLE);
            listValues.clear();
            listValues.addAll(country);
            adapter.notifyDataSetChanged();
        }else {
            lvlist.setVisibility(View.GONE);
        }
        });


        viewModel.getError().observe(this,error->{

            if(error){
                Toast.makeText(MVVMActivity.this, "Unable to Connect ,try Again later ", Toast.LENGTH_LONG).show();
                pb.setVisibility(View.GONE);
                btnRetry.setVisibility(View.VISIBLE);
            }else {
                btnRetry.setVisibility(View.GONE);
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

    private void setAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.row_country_name, R.id.txtlistText, listValues);
        lvlist.setAdapter(adapter);
        lvlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVVMActivity.this, "You Clicked + " + listValues.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVVMActivity.class);
    }
    private void buttonAction() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                viewModel.refresh();
            }
        });

    }
}
