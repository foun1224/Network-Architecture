package minato.openweather.module.apiObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by soar on 2017/2/2.
 */

public class Cod {

    @SerializedName("cod")
    @Expose
    private String cod;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

}
