package Utils;

import Collection.LabWork;

/**
 * Класс парсинга даты и времени
 */
public class DateParser {
    public int getYear(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String year = date[0].split("-")[0];
        return Integer.parseInt(year);
    }

    public int getMonth(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String month = date[0].split("-")[1];
        return Integer.parseInt(month);
    }

    public int getDay(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String day = date[0].split("-")[2];
        return Integer.parseInt(day);
    }
}
