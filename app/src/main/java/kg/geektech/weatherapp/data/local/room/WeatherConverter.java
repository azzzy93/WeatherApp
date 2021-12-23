package kg.geektech.weatherapp.data.local.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kg.geektech.weatherapp.data.models.Weather;

public class WeatherConverter {

    @TypeConverter
    public static List<Weather> toList(String value){
        Type listType = new TypeToken<List<Weather>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(List<Weather> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
