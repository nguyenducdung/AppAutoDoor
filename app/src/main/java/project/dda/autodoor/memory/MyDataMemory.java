package project.dda.autodoor.memory;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Duy Anh on 12/19/2018
 **/
public class MyDataMemory implements IDataMemory {

    static SharedPreferences sharedPreferences;
    final static String SHARED_PREFERENCES_NAME = "DataUser";


    @Override
    public void Save(Context context, String mKey, String mValue) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mKey, mValue);
        if (editor.commit()) {
            Log.d("Main","Save data success");
        }

    }

    @Override
    public String Get(Context context, String mKey) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        String mData = sharedPreferences.getString(mKey, null);
        return mData;
    }

    @Override
    public void Remove(Context context, String mKey) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(mKey);
        if (editor.commit()) {
            Log.d("Main","Remove data success");
        }
    }
}
