package project.dda.autodoor.Wifi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.dda.autodoor.R;

public class WifiSettingActivity extends AppCompatActivity {

    Button buttonApply;
    EditText editTextSSID;
    EditText editTextPASS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_setting);
        initProperties();

    }
    void initProperties()
    {
        editTextSSID = findViewById(R.id.editextSSID);
        editTextPASS = findViewById(R.id.editextPASS);
        buttonApply = findViewById(R.id.buttonApply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mSSID = editTextSSID.getText().toString();
                String mPASS = editTextPASS.getText().toString();

                if(!mSSID.isEmpty()) Wifi.SSID = mSSID;
                if(!mPASS.isEmpty()) Wifi.PASS = mPASS;

                Toast.makeText(WifiSettingActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
