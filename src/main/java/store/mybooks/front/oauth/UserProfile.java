package store.mybooks.front.oauth;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : store.mybooks.front.oauth2<br>
 * fileName       : UserProfille<br>
 * author         : masiljangajji<br>
 * date           : 3/3/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/3/24        masiljangajji       최초 생성
 */
@Getter
public class UserProfile {
    private final String oauthId;
    private final String email;
    private final String name;
    private final String birthday;
    private final String birthYear;
    private final String mobile;

    /**
     * Instantiates a new User profile.
     * 소셜로그인시 받아올 회원 정보를 저장하는 객체
     *
     * @param oauthId   the oauth id
     * @param email     the email
     * @param name      the name
     * @param birthday  the birthday
     * @param birthYear the birth year
     * @param mobile    the mobile
     */
    @Builder
    public UserProfile(String oauthId, String email, String name, String birthday, String birthYear, String mobile) {
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.birthYear = birthYear;
        this.mobile = mobile;
    }
}
