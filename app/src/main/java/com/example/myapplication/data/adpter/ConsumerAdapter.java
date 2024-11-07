package com.example.myapplication.data.adpter;

import static com.example.myapplication.ui.Utils.UiUtils.convertDate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.FinishServiceActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.model.entities.Consumer;
import com.example.myapplication.databinding.ServiceItemBinding;
import com.example.myapplication.ui.Utils.ButtonAnimationUtil;

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
        holder.binding.txtClientName.setText("Cliente: " + consumer.getName());
        holder.binding.txtEquipament.setText("Equipamento: " + consumer.getEquipment());
        holder.binding.txtContactNumber.setText("Contato: " + consumer.getContactNumber());
        holder.binding.txtCreatedAt.setText("Entrada: " + convertDate(consumer.getCreatedAt()));
        holder.binding.txtStatus.setText("Status: " + consumer.getStatus());

        if (consumer.getPhotoPath() != null && !consumer.getPhotoPath().isEmpty()) {
            holder.binding.imgService.setImageURI(Uri.parse(consumer.getPhotoPath()));
        } else {
            holder.binding.imgService.setImageResource(R.mipmap.logo_2);
        }


        ButtonAnimationUtil.setAnimation(
                holder.binding.btnCompleted,
                R.anim.button,
                R.color.success,
                R.color.text_color_hint  // cor normal
        );
        holder.binding.btnCompleted.setOnClickListener(v -> {
            Intent intent = new Intent(context, FinishServiceActivity.class);
            context.startActivity(intent);
            holder.binding.btnCompleted.setBackgroundTintList(
                    ContextCompat.getColorStateList(context, R.color.text_color_hint)
            );
        });
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