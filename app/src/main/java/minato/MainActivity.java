package minato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import minato.networkarchitecture.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)this.findViewById(R.id.linearlayout);

        DynamicAddEditText dynamicAddEditText = new DynamicAddEditText(this,linearLayout);
        dynamicAddEditText.setText("MTP12FJSPGJCM3");

    }

}
