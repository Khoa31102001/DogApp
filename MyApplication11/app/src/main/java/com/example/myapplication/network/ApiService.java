package com.example.myapplication.network;

import com.example.myapplication.model.DogBreed;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("DevTides/DogsApi/master/dogs.json")
    Call<List<DogBreed>> getAllDogs();
}
