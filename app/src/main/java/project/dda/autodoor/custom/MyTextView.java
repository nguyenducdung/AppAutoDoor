package project.dda.autodoor.custom;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Duy Anh on 12/19/2018
 **/
public class MyTextView {

    static Integer MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    static Integer WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;

    //TODO: create image
    public static TextView BasicTextView(Context context,String text,Integer size)
    {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(size);
        textView.setAllCaps(true);
        textView.setTextColor(Color.BLUE);
        textView.setLayoutParams(setParams(WRAP_CONTENT,WRAP_CONTENT));

        return textView;
    }

    //TODO: setting layout cua Image
    public static LinearLayout.LayoutParams setParams(Integer width, Integer height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        params.setMargins(20, 5, 20, 5);
        return params;
    }
}
