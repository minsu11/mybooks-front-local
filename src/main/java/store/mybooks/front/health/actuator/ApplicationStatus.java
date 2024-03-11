package store.mybooks.front.health.actuator;

import org.springframework.stereotype.Component;

/**
 * packageName    : store.mybooks.front.l4.controller
 * fileName       : actuator
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@Component
public final class ApplicationStatus {
    private boolean status = true;

    public void stopService() {
        this.status = false;
    }

    public boolean getStatus() {
        return status;
    }
}