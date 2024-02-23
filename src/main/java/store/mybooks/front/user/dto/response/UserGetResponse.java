package store.mybooks.front.user.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName    : store.mybooks.resource.user.dto.response
 * fileName       : UserGetResponse
 * author         : masiljangajji
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24        masiljangajji       최초 생성
 */


public interface UserGetResponse {

    String getUserGradeUserGradeNameId();

    String getUserStatusId();

    String getName();

    String getPhoneNumber();

    String getEmail();

    LocalDate getBirth();

    LocalDateTime getCreatedAt();

    LocalDateTime getLatestLogin();

    LocalDate getGradeChangedDate();


}
