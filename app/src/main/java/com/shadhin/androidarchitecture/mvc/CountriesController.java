package com.shadhin.androidarchitecture.mvc;

import android.widget.ListView;

import com.shadhin.androidarchitecture.model.CountriesService;
import com.shadhin.androidarchitecture.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesController {
    private MVCActivity view;
    private CountriesService service;

    public CountriesController(MVCActivity view) {
        this.view=view;
        service=new CountriesService();
        fetchCountries();

    }
    private void fetchCountries() {
        service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>(){

                    @Override
                    public void onSuccess(List<CountryModel> value) {
                        List<String> countriesName=new ArrayList<>();
                        for(CountryModel countryModelName : value){
                            countriesName.add(countryModelName.countryName);
                        }
                        view.setValue(countriesName);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                } );
    }
}
