package src.library.myObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {
    private String returnDate;
    public Order() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 15);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        returnDate = simpleDateFormat.format(cal.getTime());
    }

    public String getReturnDate() {
        return returnDate;
    }
}
