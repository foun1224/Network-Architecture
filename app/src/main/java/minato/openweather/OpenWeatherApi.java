package minato.openweather;


import minato.openweather.module.apiObject.CityWeather;
import minato.openweather.module.apiObject.Cod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by soar on 2017/1/30.
 */

public interface OpenWeatherApi {

    @GET("forecast/city")
    Call<CityWeather> getCityWeather(@Query("id") int cityId,@Query("APPID")String key);

    @GET("forecast/city")
    Observable<CityWeather> rxGetCityWeather(@Query("id") int cityId, @Query("APPID")String key);

    @GET("forecast/city")
    Observable<Cod> rxGetCityWeatherCod(@Query("id") int cityId, @Query("APPID")String key);

}
