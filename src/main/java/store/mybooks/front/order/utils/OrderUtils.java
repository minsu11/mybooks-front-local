package store.mybooks.front.order.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * packageName    : store.mybooks.front.order.utils<br>
 * fileName       : OrderUtils<br>
 * author         : minsu11<br>
 * date           : 3/16/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 3/16/24        minsu11       최초 생성<br>
 */
public class OrderUtils {
    private OrderUtils() {
    }

    /**
     * 랜덤한 문자열 생성
     *
     * @return the string
     */
    public static String createOrderNumber() {
        StringBuilder orderNumber = new StringBuilder();
        String time = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 12;
        String randomStr = new Random()
                .ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
                .toString();
        return orderNumber.append(time)
                .append(randomStr).toString();
    }


}
