package com.shadhin.androidarchitecture.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shadhin.androidarchitecture.model.CountriesService;
import com.shadhin.androidarchitecture.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesViewModel extends ViewModel {
    private CountriesService service;
    private final MutableLiveData<List<String>> countries=new MutableLiveData<>();
    private final MutableLiveData<Boolean> countryError=new MutableLiveData<>();

    public CountriesViewModel() {
        service = new CountriesService();
        fetchCountries();
    }
    public LiveData<List<String>> getCountries(){
        return countries;
    }
    public LiveData<Boolean>getError(){
        return countryError;
    }
    private void fetchCountries() {
        service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {

                    @Override
                    public void onSuccess(List<CountryModel> value) {
                        List<String> countriesName = new ArrayList<>();
                        for (CountryModel countryModelName : value) {
                            countriesName.add(countryModelName.countryName);
                        }
                        //view.onError();
                        countries.setValue(countriesName);
                        countryError.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        countryError.setValue(true);
                    }
                });
    }

    public void refresh() {
        fetchCountries();
    }
}
