package kg.geektech.weatherapp.ui.fragments.weather_fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.weatherapp.databinding.ItemWeather5DaysBinding;

public class Weather5DaysAdapter extends RecyclerView.Adapter<Weather5DaysAdapter.ViewHolder> {
    private ItemWeather5DaysBinding binding;

    public Weather5DaysAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWeather5DaysBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
      return 0;
    }







    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWeather5DaysBinding binding;

        public ViewHolder(@NonNull ItemWeather5DaysBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
