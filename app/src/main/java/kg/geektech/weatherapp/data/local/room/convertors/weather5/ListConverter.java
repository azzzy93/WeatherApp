package kg.geektech.weatherapp.data.local.room.convertors.weather5;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverter {

    @TypeConverter
    public static List<kg.geektech.weatherapp.data.models.weather_for_5_days.List> toList(String value){
        if (value == null){
            return null;
        }
        Type listType = new TypeToken<List<kg.geektech.weatherapp.data.models.weather_for_5_days.List>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toString(List<kg.geektech.weatherapp.data.models.weather_for_5_days.List> list){
        if (list == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
