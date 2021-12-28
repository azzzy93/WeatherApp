package kg.geektech.weatherapp.data.local.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import kg.geektech.weatherapp.data.models.MyWeather;

@Dao
public interface MyWeatherDao {

    @Query("SELECT * FROM myWeather")
    MyWeather getMyWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MyWeather myWeather);
}
