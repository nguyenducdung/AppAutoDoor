package project.dda.autodoor.MainAllDevices;

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
import java.util.Stack;

import project.dda.autodoor.Button.MyButton;
import project.dda.autodoor.Image.MyImage;
import project.dda.autodoor.Math.MyMath;
import project.dda.autodoor.Menu.MainActivity;
import project.dda.autodoor.Model.Pin;
import project.dda.autodoor.Model.Product;
import project.dda.autodoor.R;
import project.dda.autodoor.TextView.MyTextView;

public class AllDevicesActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout linearLayoutMain;
    Button buttonBack;
    Map<String,Integer> mapImage;
    Stack<MyPins> mPins;
    Product mProduct;

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
    void getDataFromIntent()
    {
        Intent intent = getIntent();
        String model = intent.getStringExtra("Model");
        try {
            mProduct = new Product(new JSONObject(model));
            ArrayList<String> strings = MyMath.SplitComma(MyMath.SubSquareBrackets(mProduct.Pins),"Command");

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
