package api_filmes.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static String convertDateToDateTime(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formater.format(date);
    }
}