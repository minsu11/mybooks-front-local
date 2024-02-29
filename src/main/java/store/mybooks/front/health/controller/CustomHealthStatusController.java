package store.mybooks.front.health.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import store.mybooks.front.health.actuator.ApplicationStatus;


/**
 * packageName    : store.mybooks.front.l4.controller
 * fileName       : CustomHealthStatusController
 * author         : minsu11
 * date           : 2/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/19/24        minsu11       최초 생성
 */
@RestController
public class CustomHealthStatusController {
    private final ApplicationStatus applicationStatus;

    public CustomHealthStatusController(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    @GetMapping("/health-check")
    public ResponseEntity<Void> getStatus() {
        if (applicationStatus.getStatus()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(503).build();
    }

    @PostMapping("/health/status")
    @ResponseStatus(value = HttpStatus.OK)
    public void stopStatus() {
        applicationStatus.stopService();
    }
}
