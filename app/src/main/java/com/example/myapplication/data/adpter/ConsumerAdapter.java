package com.example.myapplication.data.adpter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.databinding.ServiceItemBinding;

import java.util.ArrayList;

public class ConsumerAdapter extends RecyclerView.Adapter<ConsumerAdapter.ServiceViewHolder> {

    private final ArrayList<Consumer> consumerList;
    private final Context context;

    public ConsumerAdapter(ArrayList<Consumer> consumerList, Context context) {
        this.consumerList = consumerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceItemBinding listItemBinding;
        listItemBinding = ServiceItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ServiceViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Consumer consumer = consumerList.get(position);
        holder.binding.txtClientName.setText(consumer.getName());
        holder.binding.txtDescription.setText(consumer.getEquipment());

        // Check if the photo path is valid before setting the image
        if (consumer.getPhotoPath() != null && !consumer.getPhotoPath().isEmpty()) {
            holder.binding.imgService.setImageURI(Uri.parse(consumer.getPhotoPath()));
        } else {
            // Set a placeholder image if the path is null or empty
            holder.binding.imgService.setImageResource(R.mipmap.logo_2);
        }
    }

    @Override
    public int getItemCount() {
        return consumerList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ServiceItemBinding binding;

        public ServiceViewHolder(ServiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}