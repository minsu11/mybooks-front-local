package store.mybooks.front.cart.exception;

/**
 * packageName    : store.mybooks.front.cart.exception <br/>
 * fileName       : CookieParseException<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/7/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/7/24        Fiat_lux       최초 생성<br/>
 */
public class CookieParseException extends RuntimeException{
    public CookieParseException(String message) {
        super(message);
    }
}
