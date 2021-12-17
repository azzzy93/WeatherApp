package kg.geektech.weatherapp.ui.weather_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MyWeather;

public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<MyWeather>> liveData;

    public WeatherViewModel(){
    }

    public void getWeathers(){
        liveData = App.repository.getWeather();
    }
}
