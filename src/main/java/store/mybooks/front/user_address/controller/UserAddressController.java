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
 * packageName    : store.mybooks.front.user.user_address.controller<br>
 * fileName       : UserAddressController<br>
 * author         : masiljangajji<br>
 * date           : 2/25/24<br>
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
     * methodName : userAddressForm
     * author : masiljangajji
     * description :유저 주소 페이지로 이동
     *
     * @param model 유저의 모든 주소 정보를 담음
     * @return string
     */
    @GetMapping("/user/address")
    public String userAddressForm(Model model) {

        List<UserAddressGetResponse> list = userAddressAdaptor.findAllUserAddress();
        model.addAttribute("userAddressList", list);

        return "address";
    }

    /**
     * methodName : deleteAddress
     * author : masiljangajji
     * description : 유저의 주소를 삭제
     *
     * @param addressId id
     * @return string
     */
    @PostMapping("/user/address/delete")
    public String deleteAddress(@RequestParam(name="addressId")Long addressId) {
        userAddressAdaptor.deleteUserAddress(addressId);
        return "redirect:/user/address";
    }

    /**
     * methodName : createUserAddress
     * author : masiljangajji
     * description : 유저의 주소를 등록
     *
     * @param userAddressCreateRequest address create request
     * @return string
     */
    @PostMapping("/user/address/create")
    public String createUserAddress(@ModelAttribute UserAddressCreateRequest userAddressCreateRequest) {
        userAddressAdaptor.createUserAddress(userAddressCreateRequest);
        return "redirect:/user/address";
    }

    /**
     * methodName : modifyUserAddress
     * author : masiljangajji
     * description : 유저의 주소를 수정 (별명,상세주소)
     *
     * @param addressId id
     * @param userAddressModifyRequest    address modify request
     * @return string
     */
    @PostMapping("/user/address/modify")
    public String modifyUserAddress(@RequestParam(name = "addressId") Long addressId,
                                    @ModelAttribute UserAddressModifyRequest userAddressModifyRequest) {

        userAddressAdaptor.modifyUserAddress(addressId, userAddressModifyRequest);
        return "redirect:/user/address";
    }


}
