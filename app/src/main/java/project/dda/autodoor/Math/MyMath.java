package project.dda.autodoor.Math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Duy Anh on 12/11/2018
 **/
public class MyMath {

    //TODO: split string
    public static String[] SplitString(String s) {
        return s.split("\\-");
    }

    //TODO: cat 2 dau ngoac vuong dau va cuoi
    public static String SubSquareBrackets(String s) {
        if (s.length() > 2) return s.substring(1, s.length() - 1);
        return null;
    }

    //TODO: cat dau phay cua gia tri value
    public static ArrayList<String> SplitComma(String s,String title) {
        ArrayList<String> strings = new ArrayList<>();
        String[] temp = s.split("\\},");
        String split = "";

        for (String item : temp) {
            String model = item.substring(2, title.length()+2);

            if (model.equals(title)) {
                if (!split.equals("")) {
                    split = split.substring(0, split.length() - 1);
                    strings.add(split);
                }
                split = "";
            }
            split += item + "},";
        }

        if (!split.equals("")) {
            split = split.substring(0, split.length() - 1);
            strings.add(split);
        }
        return strings;
    }

    //TODO: chuyen Model thanh dang Hash Map
    public static Map<String,String> convertArrayToMap(ArrayList<String> strings)
    {
        Map<String,String> map = new HashMap<>();

        for (String item : strings)  {
            String[] model = item.split("\"");
            map.put(model[3],item);
        }

        return map;
    }
}
