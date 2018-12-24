package project.dda.autodoor.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import project.dda.autodoor.custom.MyButton;
import project.dda.autodoor.memory.MyDataMemory;
import project.dda.autodoor.utils.MyMath;
import project.dda.autodoor.model.User;
import project.dda.autodoor.R;
import project.dda.autodoor.TCPIp.TCPClient;
import project.dda.autodoor.wifi.Wifi;
import project.dda.autodoor.wifi.WifiSettingActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    //region TODO: Properties
    private LinearLayout linearLayoutMain;
    private MyDataMemory mMemory;

    private static Map<String, String> mapValue = new HashMap<>();
    private Map<String, Integer> mapImage;
    private Button buttonWifi;
    private boolean IsEnabled = true;
    public static boolean IsConnected = false;
    private ConnectTask mConnectTask;
    public static TCPClient mTcpClient;
    private static Integer sleep = 1000;
    private static User mUser = new User();
    private static String[] mModel;

    private ImageView imageViewUser;
    private TextView textViewName;
    private TextView textViewDescription;
    //endregion

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initProperties();
        initImage();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View view_nav_header = navigationView.getHeaderView(0);

        imageViewUser = view_nav_header.findViewById(R.id.imageView);
        textViewName = view_nav_header.findViewById(R.id.tv_nameUser);
        textViewDescription = view_nav_header.findViewById(R.id.tv_description);

        navigationView.setNavigationItemSelectedListener(this);

        getDataFromIntent();
        initButton(mModel);
        initInforUser();
    }

    //region TODO: INIT
    //TODO: khoi tao bien
    void initProperties() {
        mModel = new String[]{"R-DOOR","SLOCK"};
        buttonWifi = findViewById(R.id.buttonWifi);
        linearLayoutMain = findViewById(R.id.linearLayoutmain);
        mMemory = new MyDataMemory();

        mapImage = new HashMap<>();

        buttonWifi.setOnClickListener(this);
    }

    //TODO: khoi tao id Image
    void initImage() {
        mapImage.put("User", R.drawable.user);
        mapImage.put("R-DOOR", R.drawable.rdoor);
        mapImage.put("SLOCK", R.drawable.slock);
    }

    //TODO: tao thong tin nguoi dung
    private void initInforUser() {
        imageViewUser.setImageResource(mapImage.get("User"));
        if(mUser.UserName != null) textViewName.setText(mUser.UserName.split("\\@")[0]);
        textViewDescription.setText(mUser.UserName);
    }

    //endregion

    //TODO: tao luong rieng de connect
    public class ConnectTask extends AsyncTask<String, String, TCPClient> {

        @Override
        protected TCPClient doInBackground(String... strings) {
            //we create a TCPClient object
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.Connect();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            super.onProgressUpdate(values);
            //response received from server
            Log.d("received:", "response " + values[0]);
            //process server response here....
            Toast.makeText(MainActivity.this, values[0], Toast.LENGTH_SHORT).show();
        }
    }

    //TODO: get data from Intent
    void getDataFromIntent() {

        if(getIntent().getExtras() != null)
        {
            Intent intent = getIntent();
            mUser.Id = intent.getIntExtra("Id", 0);
            mUser.UserName = intent.getStringExtra("UserName");
            mUser.Level = intent.getIntExtra("Level", 0);
            mUser.Token = intent.getStringExtra("Token");

            String value = intent.getStringExtra("Value");
            //TODO: loai bo dau ngoac vuong
            if(value != null)
            {
                value = MyMath.SubSquareBrackets(value);

                //TODO: chuyen thanh Map xem co bao nhieu model
                mapValue = MyMath.convertArrayToMap(MyMath.SplitComma(value,"Model"));

                for (String item : mModel) {

                    String tempValue = mapValue.get(item);
                    if (tempValue != null) {

                        String data = mMemory.Get(this,item);
                        if (data != null)
                            mMemory.Remove(this,data);

                        mMemory.Save(this,item, tempValue);
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Can't get data", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    //TODO: lay data va khoi tao button
    @RequiresApi(api = Build.VERSION_CODES.M)
    void initButton(String[] models) {

        for (String item : models) {
            //TODO: lay du lieu tu bo nho
            String tempString = mMemory.Get(this,item);
            if (tempString != null) {
                //TODO: add button and set event
                final Button button = MyButton.BasicButton(this,item,mapImage.get(item));
                linearLayoutMain.addView(button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!IsEnabled)
                        {
                            String model = button.getText().toString();
                            showActivity(model);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "You must connect wifi kit", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }

    //TODO: show activity
    void showActivity(String model)
    {
        Intent intent = new Intent(this, AllDevicesActivity.class);
        intent.putExtra("Model",mapValue.get(model));
        startActivity(intent);
    }

    //endregion

    //region TODO
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, WifiSettingActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //endregion

    //TODO: Event click Button
    @Override
    public void onClick(View v) {
        //TODO: bat va tat wifi
        if (v == buttonWifi) {
            Wifi wifi = new Wifi(this);

            if (IsEnabled) {
                IsEnabled = false;
                buttonWifi.setBackgroundResource(R.drawable.ic_signal_wifi_4_bar_black_24dp);
                //wifi.Connect();
                Toast.makeText(this, "Wifi Enable", Toast.LENGTH_SHORT).show();

                try {
                    Thread.sleep(sleep);

                    mConnectTask = (ConnectTask) new ConnectTask().execute("");
                } catch (InterruptedException e) {
                    Log.e("Main Activity - ", "S: Error", e);
                }
            } else {
                IsEnabled = true;
                buttonWifi.setBackgroundResource(R.drawable.ic_signal_wifi_off_black_24dp);

                if (mTcpClient != null) {
                    mTcpClient.Disconnect();
                }
                wifi.Disconnect();

                Toast.makeText(this, "Wifi Disable", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
