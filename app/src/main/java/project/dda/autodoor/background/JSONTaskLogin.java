package project.dda.autodoor.background;

import android.content.Context;
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

import project.dda.autodoor.model.User;

/**
 * Created by Duy Anh on 12/14/2018
 **/
public class JSONTaskLogin extends AsyncTask<String, String, String> {

    Context context;
    static String mType;
    static String[] mKeys;
    public final static String LOGIN = "Login";


    public JSONTaskLogin(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {

        mType = strings[0];
        if (mType.equals(LOGIN)) return exeLogin(strings);
        return null;
    }

    //TODO: lay du lieu tu url
    String exeLogin(String... strings) {
        try {
            String mAPI = strings[1];
            String mName = strings[2];
            String mPass = strings[3];

            URL url = new URL(mAPI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("Name", mName);
            jsonParam.put("Pass", mPass);

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
            Log.e("JSONTaskLogin", " E: Error", e);
        }
        return null;
    }


    //TODO: gọi đầu tiên
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mKeys = new String[]{"Code", "Message", "Data", "Value"};
    }

    //TODO: lấy được kết quả trả về
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject object = new JSONObject(result);
            Integer index = object.getInt(mKeys[0]);

            if (index > 0) {
                if (mType.equals(LOGIN)) {
                    User mUser = getInforUser(object.getString(mKeys[2]));

                    //TODO: get Product from api
                    JSONTaskProduct jsonTaskProduct = new JSONTaskProduct(context);
                    jsonTaskProduct.execute("Product", "http://api/khachhang/getalldevices", mUser.Id.toString(),mUser.UserName,mUser.Level.toString(),mUser.Token);
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
    //TODO: get information user from api
    User getInforUser(String data)
    {
        try {
            User mUser = new User();

            JSONObject object = new JSONObject(data);

            mUser.Id = object.getInt("Id");
            mUser.UserName = object.getString("UserName");
            mUser.Level = object.getInt("Level");
            mUser.Token = object.getString("Token");

            return mUser;
        } catch (JSONException e) {
            publishProgress("Can't get Token from Information User");
        }
        return null;
    }
}
