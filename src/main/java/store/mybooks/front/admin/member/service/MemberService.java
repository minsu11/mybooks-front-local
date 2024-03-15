package store.mybooks.front.admin.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.member.adaptor.MemberAdaptor;
import store.mybooks.front.pageable.dto.response.PageResponse;
import store.mybooks.front.user.dto.response.UserGetResponse;


/**
 * packageName    : store.mybooks.front.admin.member.controller.service<br>
 * fileName       : MemberService<br>
 * author         : masiljangajji<br>
 * date           : 3/15/24<br>
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/15/24        masiljangajji       최초 생성
 */

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberAdaptor adaptor;

    public PageResponse<UserGetResponse> getAllMembers(Pageable pageable) {
        return adaptor.getPagedMembers(pageable);
    }


}
