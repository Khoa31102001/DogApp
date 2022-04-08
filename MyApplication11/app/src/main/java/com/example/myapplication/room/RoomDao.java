package com.example.myapplication.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.myapplication.model.DogBreed;

import java.util.List;

@Dao
public interface RoomDao {

    @Query("select * from DogBreed")
    List<DogBreed> getAllDogBreed();

    @Insert
    public void insert(DogBreed... dogBreeds);
    @Update
    public void update(DogBreed... dogBreeds);
    @Delete
    public void delete(DogBreed... dogBreeds);
}
