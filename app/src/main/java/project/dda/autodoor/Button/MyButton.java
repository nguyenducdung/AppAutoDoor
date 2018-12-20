package project.dda.autodoor.Button;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;
import project.dda.autodoor.R;

/**
 * Created by Duy Anh on 12/19/2018
 **/
public class MyButton {

    static Integer MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    static Integer WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;

    //TODO: them button
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static Button BasicButton(Context context,String mName ,Integer res) {

        final Button button = new Button(context);
        button.setText(mName);
        button.setBackgroundTintList(context.getApplicationContext().getColorStateList(R.color.colors));

        Drawable img = context.getResources().getDrawable(res);
        img.setBounds( 0, 0, 40, 40 );
        button.setCompoundDrawables(img,null,null,null);

        button.setLayoutParams(setParams(MATCH_PARENT,WRAP_CONTENT));
        //TODO: Send command
        return button;
    }

    //TODO: chuyen doi dp sang pixel
    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    //TODO: setting layout cua button
    public static LinearLayout.LayoutParams setParams(Integer width, Integer height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        params.setMargins(20, 5, 20, 5);
        return params;
    }
}
