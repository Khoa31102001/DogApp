package com.example.myapplication.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.DogBreed;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetroInstance;

import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogListViewModel extends ViewModel {
    private MutableLiveData<List<DogBreed>> dogList;
    public DogListViewModel(){
        dogList = new MutableLiveData<>();
    }
    public MutableLiveData<List<DogBreed>> getDogList(){
        return dogList;
    }
    public void makeApiCall(){
        ApiService apiService = RetroInstance.getRetrofitClient().create(ApiService.class);
        Call<List<DogBreed>> call = apiService.getAllDogs();
        call.enqueue(new Callback<List<DogBreed>>() {
            @Override
            public void onResponse(Call<List<DogBreed>> call, Response<List<DogBreed>> response) {
                dogList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DogBreed>> call, Throwable t) {
                dogList.postValue(null);
            }
        });
    }
}
