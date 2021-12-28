package kg.geektech.weatherapp.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.local.room.convertors.weather.CloudsConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.CoordConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.MainConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.SysConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.WeatherConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.WindConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.CityConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.CoordConvertors;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.ListConverter;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.WeatherConverter;
import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.models.weather_for_5_days.MainWeather5;

@Database(entities = {MainWeather5.class, MyWeather.class}, version = 1)
@TypeConverters({CloudsConvertor.class, CoordConvertor.class, MainConvertor.class, SysConvertor.class,
        WeatherConvertor.class, WindConvertor.class, CityConvertor.class,
        kg.geektech.weatherapp.data.local.room.convertors.weather5.CloudsConvertor.class,
        CoordConvertors.class, ListConverter.class,
        kg.geektech.weatherapp.data.local.room.convertors.weather5.MainConvertor.class,
        kg.geektech.weatherapp.data.local.room.convertors.weather5.SysConvertor.class, WeatherConverter.class,
        kg.geektech.weatherapp.data.local.room.convertors.weather5.WindConvertor.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MainWeather5Dao mainWeather5Dao();
    public abstract MyWeatherDao myWeatherDao();
}
