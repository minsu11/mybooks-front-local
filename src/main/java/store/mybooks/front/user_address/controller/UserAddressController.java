package store.mybooks.front.user_address.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.mybooks.front.user_address.adaptor.UserAddressAdaptor;
import store.mybooks.front.user_address.request.UserAddressCreateRequest;
import store.mybooks.front.user_address.request.UserAddressModifyRequest;
import store.mybooks.front.user_address.response.UserAddressGetResponse;

/**
 * packageName    : store.mybooks.front.user.user_address.controller
 * fileName       : UserAddressController
 * author         : masiljangajji
 * date           : 2/25/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/25/24        masiljangajji       최초 생성
 */
@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserAddressController {


    private final UserAddressAdaptor userAddressAdaptor;

    /**
     * User address form string.
     * 유저 주소관리 페이지로 이동
     * @param model the model 유저 주소정보를 담음
     * @return the string
     */
    @GetMapping("/user/address")
    public String userAddressForm(Model model) {

        // todo 이건 JWT달면 거기서 인증정보를 가져와서 id를 넣어줄 것임
        Long userId = 1L;

        List<UserAddressGetResponse> list = userAddressAdaptor.findAllAddressByUserId(userId);

        model.addAttribute("userId", userId);
        model.addAttribute("userAddressList", list);

        return "address";
    }

    /**
     * Delete address string.
     * 유저 주소를 삭제함 (강삭제)
     * @param addressId the address id
     * @return the string
     */
    @PostMapping("/address/delete")
    public String deleteAddress(@RequestParam(name="addressId")Long addressId) {
        // todo userId는 토큰에거 꺼내 쓸 꺼야
        userAddressAdaptor.deleteUserAddress(1L,addressId);
        return "redirect:/user/address";
    }

    /**
     * Create user address string.
     * 유저 주소를 생성함
     * @param userAddressCreateRequest the user address create request
     * @return the string
     */
    @PostMapping("/user/address/create")
    public String createUserAddress(@ModelAttribute UserAddressCreateRequest userAddressCreateRequest) {
        // todo UserId는 토큰에서 꺼내 쓸 꺼야
        userAddressAdaptor.createUserAddress(1L, userAddressCreateRequest);
        return "redirect:/user/address";
    }

    /**
     * Modify user address string.
     * 유저 주소를 변경함 (별명,상세주소)
     * @param addressId                the address id
     * @param userAddressModifyRequest the user address modify request
     * @return the string
     */
    @PostMapping("/user/address/modify")
    public String modifyUserAddress(@RequestParam(name = "addressId") Long addressId,
                                    @ModelAttribute UserAddressModifyRequest userAddressModifyRequest) {

        // todo UserId는 토큰에서 꺼내 쓸 꺼야

        userAddressAdaptor.modifyUserAddress(1L, addressId, userAddressModifyRequest);
        return "redirect:/user/address";
    }


}
