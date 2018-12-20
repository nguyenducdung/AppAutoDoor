package project.dda.autodoor.Image;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Duy Anh on 12/19/2018
 **/
public class MyImage {

    static Integer MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    static Integer WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;

    //TODO: create image
    public static ImageView BasicImage(Context context,Integer res)
    {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(setParams(WRAP_CONTENT,WRAP_CONTENT));
        imageView.clearColorFilter();
        imageView.setImageResource(res);

        return imageView;
    }

    //TODO: setting layout cua Image
    public static LinearLayout.LayoutParams setParams(Integer width, Integer height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        params.setMargins(20, 5, 20, 5);
        return params;
    }
}
