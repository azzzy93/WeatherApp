package kg.geektech.weatherapp.ui.fragments.weather_fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.data.local.room.AppDatabase;
import kg.geektech.weatherapp.data.local.room.MainWeather5Dao;
import kg.geektech.weatherapp.data.local.room.MyWeatherDao;
import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends Fragment implements LocationListener {

    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private Weather5DaysAdapter adapter;
    private BroadcastReceiver receiver;
    @Inject
    MyWeatherDao myWeatherDao;
    @Inject
    MainWeather5Dao mainWeather5Dao;
    private final String[] PERMS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private LocationManager locationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new Weather5DaysAdapter();
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(requireActivity(), PERMS, 1);
        getCurrentLocation();


        if (getArguments() != null) {
            Bundle bundle = getArguments();
            Double lat = bundle.getDouble("keyLat");
            Double lon = bundle.getDouble("keyLon");
            viewModel.getWeathers(lat, lon);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                ActivityCompat.requestPermissions(requireActivity(), PERMS, 1);
            }
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    1000, 0, this
            );
        }
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

        getBroadcastReceiver();
        initListeners();
    }

    private void getBroadcastReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    if (isOnline(context)) {
                        dialog(true);
                    } else {
                        dialog(false);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };
        requireActivity().registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void dialog(boolean b) {
        binding.recycler.setAdapter(adapter);

        if (b) {
            Toast.makeText(requireContext(), "Интернет подключен", Toast.LENGTH_SHORT).show();
            getData();
        } else {
            Toast.makeText(requireContext(), "Интернет отключен", Toast.LENGTH_SHORT).show();
            adapter.setList(mainWeather5Dao.getMainWeather5().getList());
            setDataView(myWeatherDao.getMyWeather());
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
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
                    setDataView(resource.data);
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

        viewModel.liveData5.observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case SUCCESS: {
                    adapter.setList(resource.data.getList());
                    Toast.makeText(requireActivity(), "SUCCESS RECYCLER", Toast.LENGTH_SHORT).show();
                    break;
                }
                case ERROR: {
                    Toast.makeText(requireActivity(), "ERROR RECYCLER", Toast.LENGTH_SHORT).show();
                    break;
                }
                case LOADING: {
                    Toast.makeText(requireActivity(), "LOADING RECYCLER", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });
    }

    public void setDataView(MyWeather myWeather) {
        String current_date = getTime(myWeather.getDt(), "EEE, dd MMM yyyy  |  HH:mm:ss", "GMT+6");
        String loc = myWeather.getName() + ", " + myWeather.getSys().getCountry();
        String iconUrl = "https://openweathermap.org/img/wn/" + myWeather.getWeather().get(0).getIcon() + "@2x.png";
        String weatherCurrent = myWeather.getWeather().get(0).getMain();
        String temp = new DecimalFormat("0").format(myWeather.getMain().getTemp());
        String tempMax = new DecimalFormat("0").format(myWeather.getMain().getTempMax()) + "°C";
        String tempMin = new DecimalFormat("0").format(myWeather.getMain().getTempMin()) + "°C";
        String humidity = myWeather.getMain().getHumidity() + "%";
        String pressure = myWeather.getMain().getPressure() + "mBar";
        String windSpeed = myWeather.getWind().getSpeed() + "m/s";
        String sunset = getTime(myWeather.getSys().getSunset(), "HH:MM", "GMT+6");
        String sunrise = getTime(myWeather.getSys().getSunrise(), "HH:MM", "GMT+6");
        Integer d = myWeather.getSys().getSunset() - myWeather.getSys().getSunrise();
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
    }

    private String getTime(Integer timeInt, String timeFormat, String gmt) {
        long time = timeInt * (long) 1000;
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(TimeZone.getTimeZone(gmt));
        return format.format(date);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        unRegisterNetwork();
    }

    private void unRegisterNetwork() {
        try {
            requireActivity().unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.e("aziz", "Lat: " + location.getLatitude() + "\nLong : " + location.getLongitude());

        if (getArguments() == null) {
            viewModel.getWeathers(location.getLatitude(), location.getLongitude());
            getBroadcastReceiver();
        }
    }
}