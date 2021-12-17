package kg.geektech.weatherapp;

import android.app.Application;

import kg.geektech.weatherapp.data.remote.RetroFitClient;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.repository.MainRepository;

public class App extends Application {
    public static WeatherApi api;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        RetroFitClient client = new RetroFitClient();
        api = client.provideApi();
        repository = new MainRepository();
    }
}
