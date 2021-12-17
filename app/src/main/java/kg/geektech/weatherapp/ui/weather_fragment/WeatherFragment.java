package kg.geektech.weatherapp.ui.weather_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        viewModel.getWeathers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.liveData.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case SUCCESS: {
                    String current_date = getTime(resource.data.getDt(), "EEE, dd MMM yyyy  |  HH:mm:ss", "GMT+6");
                    String loc = resource.data.getName() + ", " + resource.data.getSys().getCountry();
                    String temp = new DecimalFormat("0").format(resource.data.getMain().getTemp());
                    String tempMax = new DecimalFormat("0").format(resource.data.getMain().getTempMax()) + "°C";
                    String tempMin = new DecimalFormat("0").format(resource.data.getMain().getTempMin()) + "°C";
                    String humidity = resource.data.getMain().getHumidity() + "%";
                    String pressure = resource.data.getMain().getPressure() + "mBar";
                    String windSpeed = resource.data.getWind().getSpeed() + "m/s";
                    String sunset = getTime(resource.data.getSys().getSunset(), "HH:MM", "GMT+6");
                    String sunrise = getTime(resource.data.getSys().getSunrise(), "HH:MM", "GMT+6");
                    Integer d = resource.data.getSys().getSunset() - resource.data.getSys().getSunrise();
                    String daytime = getTime(d, "HH'h' MM'm'","GMT");

                    binding.tvDate.setText(current_date);
                    binding.tvLocation.setText(loc);
                    binding.tvTemp.setText(temp);
                    binding.tvMaxTemp.setText(tempMax);
                    binding.tvMinTemp.setText(tempMin);
                    binding.tvHumidityProc.setText(humidity);
                    binding.tvPressureMBar.setText(pressure);
                    binding.tvWindKmH.setText(windSpeed);
                    binding.tvSunsetPm.setText(sunset);
                    binding.tvSunriseAm.setText(sunrise);
                    binding.tvSunny.setText(resource.data.getWeather().get(0).getMain());
                    String iconUrl = "https://openweathermap.org/img/wn/" + resource.data.getWeather().get(0).getIcon() + "@2x.png";
                    Glide.with(requireContext()).load(iconUrl).into(binding.ivSunny);
                    binding.tvDaytimeHM.setText(daytime);

                    Toast.makeText(requireActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    break;
                }
                case ERROR: {
                    Toast.makeText(requireActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    Toast.makeText(requireActivity(), "LOADING", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    private String getTime(Integer timeInt, String timeFormat, String gmt){
        long time = timeInt * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone(gmt));
        return format.format(date);
    }
}