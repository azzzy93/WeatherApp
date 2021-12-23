package kg.geektech.weatherapp.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.models.weather_for_5_days.MainWeather5;

@Database(entities = {MainWeather5.class, MyWeather.class}, version = 1)
@TypeConverters({ListConverter.class, WeatherConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MainWeather5Dao mainWeather5Dao();
    public abstract MyWeatherDao myWeatherDao();
}
