package com.shadhin.androidarchitecture.mvp;

import com.shadhin.androidarchitecture.model.CountriesService;
import com.shadhin.androidarchitecture.model.CountryModel;
import com.shadhin.androidarchitecture.mvc.MVCActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesPresenter {
    private View view;
    private CountriesService service;

    public CountriesPresenter(View view) {
        this.view = view;
        service = new CountriesService();
        fetchCountries();

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
                        view.setValue(countriesName);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError();
                    }
                });
    }

    public void refresh() {
        fetchCountries();
    }

    public interface View{
        void setValue(List<String > countries);
        void  onError();
    }
}
