package store.mybooks.front.cart;

import org.springframework.context.ApplicationEvent;

/**
 * packageName    : store.mybooks.front.cart <br/>
 * fileName       : LogoutCartDataMoveEvent<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/12/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/12/24        Fiat_lux       최초 생성<br/>
 */
public class LogoutCartDataMoveEvent extends ApplicationEvent {
    public LogoutCartDataMoveEvent(Object source) {
        super(source);
    }
}
