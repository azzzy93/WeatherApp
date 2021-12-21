package kg.geektech.weatherapp.ui.fragments.weather_fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.weatherapp.databinding.ItemWeather5DaysBinding;

public class Weather5DaysAdapter extends RecyclerView.Adapter<Weather5DaysAdapter.ViewHolder> {
    private ItemWeather5DaysBinding binding;
    private List<kg.geektech.weatherapp.data.models.weather_for_5_days.List> list;

    public Weather5DaysAdapter() {
        list = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<kg.geektech.weatherapp.data.models.weather_for_5_days.List> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWeather5DaysBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
      return list.size();
    }







    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWeather5DaysBinding binding;

        public ViewHolder(@NonNull ItemWeather5DaysBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(kg.geektech.weatherapp.data.models.weather_for_5_days.List list) {
            String iconUrl = "https://openweathermap.org/img/wn/" + list.getWeather().get(0).getIcon() + "@2x.png";
            String date = list.getDtTxt();
            String max = list.getMain().getTempMax() + "°C";
            String min = list.getMain().getTempMin() + "°C";

            Glide.with(binding.ivWeather).load(iconUrl).into(binding.ivWeather);
            binding.tvDate.setText(date);
            binding.tempMax.setText(max);
            binding.tempMin.setText(min);
        }
    }
}
