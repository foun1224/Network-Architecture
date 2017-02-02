package minato.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import minato.component.ProgressControlReceiver;
import minato.network.architecture.retrofit.ApiProxy;
import minato.openweather.WeatherConfig;
import minato.openweather.module.apiObject.CityWeather;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    ProgressControlReceiver progressReceiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressReceiver = new ProgressControlReceiver(this);
        ApiProxy.getCityWeather(WeatherConfig.CityID, new Action1<CityWeather>() {
            @Override
            public void call(CityWeather cityWeather) {
                Log.i("","");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        progressReceiver.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        progressReceiver.onPause();
    }



}
