package com.example.myapplication.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "DogBreed")
public class DogBreed implements Serializable {
    private static final long serialVersionUID = 5528582558282683513L;
    @PrimaryKey()
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    private String life_span;
    @ColumnInfo(name = "origin")
    @SerializedName("origin")
    private String origin;
    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String url;

    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    private String bred_for;

    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    private String temperament;

    public DogBreed(){

    }

    public DogBreed( String name, String life_span, String origin, String url, String bred_for, String temperament) {
        this.id = id;
        this.name = name;
        this.life_span = life_span;
        this.origin = origin;
        this.url = url;
        this.bred_for = bred_for;
        this.temperament = temperament;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DogBreed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", life_span='" + life_span + '\'' +
                ", origin='" + origin + '\'' +
                ", url='" + url + '\'' +
                ", bred_for='" + bred_for + '\'' +
                ", temperament='" + temperament + '\'' +
                '}';
    }



    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, String url) {
        Picasso.get().load(url).resize(800,600).into(imageView);
    }
}
