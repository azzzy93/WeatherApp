package kg.geektech.weatherapp.ui.fragments.weather_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    private MainRepository repository;

    public LiveData<Resource<MyWeather>> liveData;

    @Inject
    public WeatherViewModel(MainRepository repository){
        this.repository = repository;
    }

    public void getWeathers(String cityName){
        liveData = repository.getWeather(cityName);
    }
}
