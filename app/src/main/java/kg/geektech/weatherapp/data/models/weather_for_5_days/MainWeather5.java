
package kg.geektech.weatherapp.data.models.weather_for_5_days;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.local.room.convertors.weather5.CityConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.ListConverter;

@Entity
public class MainWeather5 {

    @PrimaryKey(autoGenerate = true)
    private long idRoom;
    private String cod;
    private Integer message;
    private Integer cnt;
    @TypeConverters({ListConverter.class})
    private java.util.List<List> list = null;
    @TypeConverters({CityConvertor.class})
    private City city;

    public MainWeather5() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }
}
