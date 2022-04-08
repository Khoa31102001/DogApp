package com.example.myapplication.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.DogAdapter;
import com.example.myapplication.R;
import com.example.myapplication.model.DogBreed;
import com.example.myapplication.room.DogDatabase;
import com.example.myapplication.room.RoomDao;
import com.example.myapplication.viewModel.DogListViewModel;

import java.util.List;

public class ListFragment extends Fragment {

    private List<DogBreed> dogBreeds;
    private DogAdapter adapter;
    private DogListViewModel viewModel;
    private DogDatabase dogDatabase;
    private RoomDao roomDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv_dog);

        rv.setLayoutManager(new GridLayoutManager(view.getContext(), 2));



        dogDatabase = DogDatabase.getInstance(view.getContext());
        roomDao = dogDatabase.roomDao();
        viewModel = ViewModelProviders.of(this).get(DogListViewModel.class);
        viewModel.getDogList().observe(this, new Observer<List<DogBreed>>() {
            @Override
            public void onChanged(List<DogBreed> dogBreeds1) {
                if (dogBreeds1 != null) {
                    dogBreeds = dogBreeds1;
                    adapter = new DogAdapter(dogBreeds);
                    rv.setAdapter(adapter);
                    adapter.setDogBreeds(dogBreeds);
                    List<DogBreed> listDb = roomDao.getAllDogBreed();
                    //System.out.println(listDb);
                    if(listDb.size()>0){
                        return;
                    }
                    for (DogBreed dogBreed : dogBreeds) {
                        roomDao.insert(dogBreed);
                    }
                } else {
                    Log.e("ngu", "ngu");
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu) ;
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}