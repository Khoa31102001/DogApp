package com.example.myapplication.Adapter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> implements Filterable {

    private static List<DogBreed> dogBreeds;
    private List<DogBreed> dogBreedsAll;
    public DogAdapter(List<DogBreed> dogBreeds)
    {
        this.dogBreeds = dogBreeds;
        this.dogBreedsAll = new ArrayList<>(dogBreeds);
        notifyDataSetChanged();
    }
    public void setDogBreeds(List<DogBreed> dogBreeds){
        this.dogBreeds = dogBreeds;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        DogBreed dogBreed = dogBreeds.get(position);
        if(dogBreed==null){
            return;
        }
        Picasso.get().load(dogBreeds.get(position).getUrl()).into(holder.ivAvatar);
        holder.tvName.setText(dogBreed.getName());
        holder.tvCountry.setText(dogBreed.getOrigin());
    }



    @Override
    public int getItemCount() {
        if(dogBreeds!=null)
        {
            return dogBreeds.size();
        }
        return 0;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<DogBreed> filterdList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filterdList.addAll(dogBreedsAll);
            }else{
                for(DogBreed dogBreed: dogBreedsAll){
                    if (dogBreed.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterdList.add(dogBreed);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterdList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dogBreeds.clear();
            dogBreeds.addAll((Collection<? extends DogBreed>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvCountry;
        public ImageView ivAvatar;
        public ViewHolder(View view) {
            super(view);
            tvName=view.findViewById(R.id.tv_name);
            tvCountry = view.findViewById(R.id.tv_country);
            ivAvatar=view.findViewById(R.id.iv_avatar);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DogBreed dogBreed = dogBreeds.get(getAbsoluteAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dogBreed",dogBreed);
                    Navigation.findNavController(view).navigate(R.id.detailsFragment,bundle);
                }
            });
        }

    }
}