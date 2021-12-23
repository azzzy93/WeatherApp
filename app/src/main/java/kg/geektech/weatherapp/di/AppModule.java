package kg.geektech.weatherapp.di;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapp.data.local.room.AppDatabase;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import kg.geektech.weatherapp.data.repository.MainRepository;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static MainRepository provideManiRepository(WeatherApi api){
        return new MainRepository(api);
    }

    @Provides
    public static WeatherApi provideApi(Retrofit retrofit){
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static OkHttpClient provideOkHttpClient(Interceptor loggingInterceptor){
        return new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    public static Interceptor provideLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
}
