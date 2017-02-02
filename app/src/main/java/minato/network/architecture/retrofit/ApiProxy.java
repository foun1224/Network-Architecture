package minato.network.architecture.retrofit;

import minato.component.ProgressController;
import minato.openweather.OpenWeatherApi;
import minato.openweather.WeatherConfig;
import minato.openweather.module.apiObject.CityWeather;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by soar on 2017/1/30.
 */

public class ApiProxy {

    private static Retrofit mRetrofit = new Retrofit.Builder()
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                            .baseUrl(WeatherConfig.BaseUrl)
                                            .build();

    private static OpenWeatherApi mOpenWeatherApi = mRetrofit.create(OpenWeatherApi.class);
    private static ProgressController mProgressController = new ProgressController();


    private static Action1<Throwable> mNetworkErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {

        }
    };

    private static Action1 mDisableProgressAction = new Action1() {
        @Override
        public void call(Object o) {
            mProgressController.dimiss();
        }
    };

    private static <T> void executeApi(Observable observable, Action1<T> action) {
        mProgressController.displayProgress("","");
        observable.observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .unsubscribeOn(Schedulers.io())
                  .doOnNext(mDisableProgressAction)
                  .subscribe(action, mNetworkErrorAction);
    }

    public static <T> void getCityWeather(int cityId,Action1<T> apiSuccessAction) {
        Observable<CityWeather> observable = mOpenWeatherApi.rxGetCityWeather(cityId,WeatherConfig.APIKey);
        executeApi(observable,apiSuccessAction);
    }

}
