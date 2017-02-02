
package minato.openweather.module.apiObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys_ {

    @SerializedName("pod")
    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
