package project.dda.autodoor.BackGround;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import project.dda.autodoor.Math.MyMath;
import project.dda.autodoor.Menu.MainActivity;
import project.dda.autodoor.Model.User;

/**
 * Created by Duy Anh on 12/17/2018
 **/
public class JSONTaskProduct extends AsyncTask<String, String, String> {
    Context context;
    static String mType;
    static String[] mKeys;
    public final static String PRODUCT = "Product";
    User mUser;

    String temp;

    {
        temp = "{\n" +
                "    \"Code\": 1,\n" +
                "    \"Message\": null,\n" +
                "    \"Data\": null,\n" +
                "    \"Value\": [\n" +
                "        {\n" +
                "            \"Model\": \"R-DOOR\",\n" +
                "            \"Id\": 2,\n" +
                "            \"Name\": \"26 F9 Bách Khoa\",\n" +
                "            \"Address\": \"18fe34f85476\",\n" +
                "            \"DongId\": 5,\n" +
                "            \"Offline\": 0,\n" +
                "            \"Status\": \"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\n" +
                "            \"Pins\": [\n" +
                "                {\n" +
                "                    \"Command\": \"#DW1H;#DL100;#DW1L;\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"UP\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Cuốn lên\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 2,\n" +
                "                    \"IsHigh\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#DW2H;#DL100;#DW2L;\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"DOWN\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Cuốn xuống\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 4,\n" +
                "                    \"IsHigh\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#DW3H;#DL100;#DW3L;\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"STOP\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Dừng\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 8,\n" +
                "                    \"IsHigh\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#DW1L;#DW2L;#AL+OUTPUT=3,0\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"UNLOCK\",\n" +
                "                    \"Value\": 0,\n" +
                "                    \"Description\": \"Mở khóa\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 0,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 8,\n" +
                "                    \"IsHigh\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#AL+OUTPUT=3,1\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"LOCK\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Khóa\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 8,\n" +
                "                    \"IsHigh\": false\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"Model\": \"SLOCK\",\n" +
                "            \"Id\": 4,\n" +
                "            \"Name\": \"BKS-06ad\",\n" +
                "            \"Address\": \"18fe34f806ad\",\n" +
                "            \"DongId\": 3,\n" +
                "            \"Offline\": 0,\n" +
                "            \"Status\": \"0200008001020408818181\",\n" +
                "            \"Pins\": [\n" +
                "                {\n" +
                "                    \"Command\": \"#AL+ARM=1\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"SYSTEM\",\n" +
                "                    \"Name\": \"ARM\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Bật cảnh báo\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 2,\n" +
                "                    \"StatusMask\": 1,\n" +
                "                    \"IsHigh\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#AL+ARM=0\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"SYSTEM\",\n" +
                "                    \"Name\": \"DISARM\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Tắt cảnh báo\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 2,\n" +
                "                    \"StatusMask\": 0,\n" +
                "                    \"IsHigh\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#AL+OUTPUT=1,1\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"LOCK\",\n" +
                "                    \"Value\": 1,\n" +
                "                    \"Description\": \"Khóa\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 2,\n" +
                "                    \"IsHigh\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#AL+OUTPUT=1,0\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"OUTPUTS\",\n" +
                "                    \"Name\": \"UNLOCK\",\n" +
                "                    \"Value\": 255,\n" +
                "                    \"Description\": \"Mở khóa\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 0,\n" +
                "                    \"StatusIndex\": 0,\n" +
                "                    \"StatusMask\": 2,\n" +
                "                    \"IsHigh\": false\n" +
                "                },\n" +
                "                {\n" +
                "                    \"Command\": \"#AL+CLEAR\",\n" +
                "                    \"Disabled\": false,\n" +
                "                    \"Group\": \"ALARMS\",\n" +
                "                    \"Name\": \"ALARM\",\n" +
                "                    \"Value\": 254,\n" +
                "                    \"Description\": \"Báo động\",\n" +
                "                    \"UserControlFlag\": 1,\n" +
                "                    \"Level\": 1,\n" +
                "                    \"StatusIndex\": 1,\n" +
                "                    \"StatusMask\": 2,\n" +
                "                    \"IsHigh\": false\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public JSONTaskProduct(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {

        mType = strings[0];
        if (mType.equals(PRODUCT)) return exeProduct(strings);
        return null;
    }

    //TODO: lay du lieu cac Product xuong tu url
    String exeProduct(String... strings) {
        try {
            String mAPI = strings[1];

            mUser.Id = Integer.getInteger(strings[2]);
            mUser.UserName = strings[3];
            mUser.Level = Integer.getInteger(strings[4]);
            mUser.Token = strings[5];

            URL url = new URL(mAPI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("Token", mUser.Token);

            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            conn.disconnect();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JSONTaskProduct", " E: Error", e);
        }

        return null;
    }

    //TODO: gọi đầu tiên
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mKeys = new String[]{"Code", "Message", "Data", "Value"};

        mUser = new User();
    }

    //TODO: lấy được kết quả trả về
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        try {
            JSONObject object = new JSONObject(temp);
            Integer index = object.getInt(mKeys[0]);

            if (index > 0) {

                if (mType.equals(PRODUCT)) {
                    String value = object.getString(mKeys[3]);
                    showActivityMenu(value,mUser);
                }
            } else if (index < 0) {
                publishProgress(object.getString(mKeys[1]));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSONTaskLogin", " E: Erro", e);
        }
    }

    //TODO: Dùng để cập nhật giao diện lúc runtime
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
    }

    //TODO: hien ra man hinh menu
    void showActivityMenu(String value,User user) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(mKeys[3], value);
        intent.putExtra("Id",user.Id);
        intent.putExtra("UserName",user.UserName);
        intent.putExtra("Level",user.Level);
        intent.putExtra("Token",user.Token);
        context.startActivity(intent);
    }

}

