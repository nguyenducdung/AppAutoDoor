package project.dda.autodoor.memory;

import android.content.Context;

/**
 * Created by Duy Anh on 12/19/2018
 **/
public interface IDataMemory {
    void Save(Context context, String mKey, String mValue);
    String Get(Context context, String mKey);
    void Remove(Context context, String mKey);
}
