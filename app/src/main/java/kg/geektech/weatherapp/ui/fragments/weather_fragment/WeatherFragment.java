package kg.geektech.weatherapp.ui.fragments.weather_fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.data.models.weather_for_5_days.List;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends Fragment {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private WeatherFragmentArgs args;
    private Weather5DaysAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new Weather5DaysAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        args = WeatherFragmentArgs.fromBundle(getArguments());
        String cityName = args.getCityName();

        viewModel.getWeathers(cityName);
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

        getData();
        initListeners();
        initRecycler();
    }

    private void initRecycler() {
        binding.recycler.setAdapter(adapter);

        viewModel.liveData5.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status){
                case SUCCESS:{
                    Toast.makeText(requireActivity(), "SUCCESS RECYCLER", Toast.LENGTH_SHORT).show();
                    adapter.setList(resource.data.getList());
                    break;
                }
                case ERROR:{
                    Toast.makeText(requireActivity(), "ERROR RECYCLER", Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING:{
                    Toast.makeText(requireActivity(), "LOADING RECYCLER", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    private void initListeners() {
        binding.ivBackgroundCorner.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
            navController.navigate(R.id.citySelectFragment);
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void getData() {
        viewModel.liveData.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case SUCCESS: {
                    String current_date = getTime(resource.data.getDt(), "EEE, dd MMM yyyy  |  HH:mm:ss", "GMT+6");
                    String loc = resource.data.getName() + ", " + resource.data.getSys().getCountry();
                    String iconUrl = "https://openweathermap.org/img/wn/" + resource.data.getWeather().get(0).getIcon() + "@2x.png";
                    String weatherCurrent = resource.data.getWeather().get(0).getMain();
                    String temp = new DecimalFormat("0").format(resource.data.getMain().getTemp());
                    String tempMax = new DecimalFormat("0").format(resource.data.getMain().getTempMax()) + "°C";
                    String tempMin = new DecimalFormat("0").format(resource.data.getMain().getTempMin()) + "°C";
                    String humidity = resource.data.getMain().getHumidity() + "%";
                    String pressure = resource.data.getMain().getPressure() + "mBar";
                    String windSpeed = resource.data.getWind().getSpeed() + "m/s";
                    String sunset = getTime(resource.data.getSys().getSunset(), "HH:MM", "GMT+6");
                    String sunrise = getTime(resource.data.getSys().getSunrise(), "HH:MM", "GMT+6");
                    Integer d = resource.data.getSys().getSunset() - resource.data.getSys().getSunrise();
                    String daytime = getTime(d, "HH'h' MM'm'", "GMT");

                    binding.tvDate.setText(current_date);
                    binding.tvLocation.setText(loc);
                    Glide.with(requireContext()).load(iconUrl).into(binding.ivSunny);
                    binding.tvTemp.setText(temp);
                    binding.tvMaxTemp.setText(tempMax);
                    binding.tvMinTemp.setText(tempMin);
                    binding.tvHumidityProc.setText(humidity);
                    binding.tvPressureMBar.setText(pressure);
                    binding.tvWindKmH.setText(windSpeed);
                    binding.tvSunsetPm.setText(sunset);
                    binding.tvSunriseAm.setText(sunrise);
                    binding.tvSunny.setText(weatherCurrent);
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

    private String getTime(Integer timeInt, String timeFormat, String gmt) {
        long time = timeInt * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone(gmt));
        return format.format(date);
    }
}