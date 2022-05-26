package DataBase;

import java.sql.Date;
import java.time.LocalDate;

public class DateConverter {
    private DateConverter() {
    }

    public static Date convertToDate(LocalDate localDate){
        Date date = Date.valueOf(localDate);
        return date;
    }

    public static LocalDate convertToLocalDate(Date date){
        return date.toLocalDate();
    }
}
