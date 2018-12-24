package project.dda.autodoor.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import project.dda.autodoor.model.MyPins;
import project.dda.autodoor.custom.MyButton;
import project.dda.autodoor.custom.MyImage;
import project.dda.autodoor.utils.MyMath;
import project.dda.autodoor.model.Pin;
import project.dda.autodoor.model.Product;
import project.dda.autodoor.R;
import project.dda.autodoor.custom.MyTextView;

public class AllDevicesActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linearLayoutMain;
    private Button buttonBack;
    private Map<String,Integer> mapImage;
    private Stack<MyPins> mPins;
    private Product mProduct;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_devices);

        initProperties();
        initImage();
        getDataFromIntent();
        showLayout();
    }

    //TODO: khoi tao bien
    void initProperties()
    {
        linearLayoutMain = findViewById(R.id.linearlayoutMain);
        buttonBack = findViewById(R.id.buttonBack);

        mapImage = new HashMap<>();
        mPins = new Stack<>();

        buttonBack.setOnClickListener(this);
    }

    //TODO: khoi tao anh
    void initImage()
    {
        mapImage.put("R-DOOR", R.drawable.rdoor);
        mapImage.put("SLOCK", R.drawable.slock);
        mapImage.put("UP",R.drawable.ic_arrow_upward_black_24dp);
        mapImage.put("DOWN",R.drawable.ic_arrow_downward_black_24dp);
        mapImage.put("STOP",R.drawable.ic_stop_black_24dp);
        mapImage.put("UNLOCK",R.drawable.ic_lock_outline_black_24dp);
        mapImage.put("LOCK",R.drawable.ic_lock_open_black_24dp);
        mapImage.put("ARM",R.drawable.ic_alarm_on_black_24dp);
        mapImage.put("DISARM",R.drawable.ic_alarm_off_black_24dp);
        mapImage.put("ALARM",R.drawable.ic_alarm_add_black_24dp);
    }

    //TODO: get data from intent
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void getDataFromIntent()
    {
        Intent intent = getIntent();
        String model = intent.getStringExtra("Model");
        try {
            mProduct = new Product(new JSONObject(model));
            ArrayList<String> strings = MyMath.SplitComma(Objects.requireNonNull(MyMath.SubSquareBrackets(mProduct.Pins)),"Command");

            if(strings != null)
            {
                for (String item : strings) {
                    Pin pin = new Pin(new JSONObject(item));

                    mPins.push(new MyPins(pin.Name,pin));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //TODO: show layout
    @RequiresApi(api = Build.VERSION_CODES.M)
    void showLayout()
    {
        ImageView imageView = MyImage.BasicImage(this,mapImage.get(mProduct.Model));
        TextView textViewTitle = MyTextView.BasicTextView(this,mProduct.Model,10);
        TextView textViewDescription = MyTextView.BasicTextView(this,mProduct.Name,10);

        linearLayoutMain.addView(imageView);
        linearLayoutMain.addView(textViewTitle);
        linearLayoutMain.addView(textViewDescription);

        while (mPins.size() > 0)
        {
            MyPins myPins = mPins.peek();
            Button button = MyButton.BasicButton(this,myPins.Name,mapImage.get(myPins.Name));
            button.setOnClickListener(this);

            linearLayoutMain.addView(button);
            mPins.pop();
        }


    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;

        if(button == buttonBack)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        for (MyPins pin : mPins) {
            if(pin.Name.equals(button.getText().toString()))
            {
                //MainActivity.mTcpClient.SendMessage();
            }
        }
    }

}
