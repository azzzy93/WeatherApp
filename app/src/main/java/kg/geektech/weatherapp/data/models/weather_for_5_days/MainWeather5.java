
package kg.geektech.weatherapp.data.models.weather_for_5_days;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MainWeather5 {

    @PrimaryKey(autoGenerate = true)
    private long idRoom;
    private String cod;
    private Integer message;
    private Integer cnt;
    private java.util.List<List> list = null;
    @Embedded
    private City city;

    public MainWeather5(long idRoom, String cod, Integer message, Integer cnt, java.util.List<List> list, City city) {
        this.idRoom = idRoom;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

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
