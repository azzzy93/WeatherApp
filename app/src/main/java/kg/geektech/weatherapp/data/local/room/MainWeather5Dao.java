package kg.geektech.weatherapp.data.local.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import kg.geektech.weatherapp.data.models.weather_for_5_days.MainWeather5;

@Dao
public interface MainWeather5Dao {

    @Query("SELECT * FROM MainWeather5")
    MainWeather5 getMainWeather5();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MainWeather5 mainWeather5);
}
