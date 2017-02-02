package minato.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;


import minato.R;
import minato.network.architecture.retrofit.ApiProxy;
import minato.openweather.WeatherConfig;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)this.findViewById(R.id.linearlayout);
        ApiProxy.getCityWeather(WeatherConfig.CityID);

    }

}
