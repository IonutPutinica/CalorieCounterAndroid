package util;
import java.text.DecimalFormat;

public class Utils {

    public static String formatNumber(int value)
    {
        //this is how the numbers are going to be displayed
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formatted = formatter.format(value);
        return formatted;
    }

}
