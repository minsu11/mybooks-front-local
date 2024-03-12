package store.mybooks.front.admin.delivery_rule.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleModifyRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleRegisterRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleResponse;
import store.mybooks.front.admin.delivery_rule.service.DeliveryRuleService;
import store.mybooks.front.admin.delivery_rule_name.dto.DeliveryRuleNameResponse;
import store.mybooks.front.admin.delivery_rule_name.service.DeliveryRuleNameService;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule.controller <br/>
 * fileName       : DeliveryRuleController<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/11/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/11/24        Fiat_lux       최초 생성<br/>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/delivery-rule")
public class DeliveryRuleController {
    private final DeliveryRuleService deliveryRuleService;
    private final DeliveryRuleNameService deliveryRuleNameService;

    @GetMapping
    public String viewDeliveryRule(Model model) {
        List<DeliveryRuleResponse> allDeliveryRule = deliveryRuleService.getAllDeliveryRule();
        model.addAttribute("deliveryList", allDeliveryRule);

        return "admin/view/delivery-rule/delivery-rule-view";
    }

    @GetMapping("/register")
    public String registerDeliveryRuleView(Model model) {
        List<DeliveryRuleNameResponse> allDeliveryRuleNames = deliveryRuleNameService.getAllDeliveryRuleNames();
        model.addAttribute("deliveryRuleNameList", allDeliveryRuleNames);

        return "admin/view/delivery-rule/delivery-rule-register-view";
    }

    @PostMapping("/register")
    public String registerDeliveryRuleForm(@ModelAttribute DeliveryRuleRegisterRequest deliveryRuleRegisterRequest) {
        if (deliveryRuleService.createDeliveryRule(deliveryRuleRegisterRequest)) {
            return "redirect:/admin/delivery-rule";
        }
        return "redirect:/admin/delivery-rule/register";
    }

    @GetMapping("/modify/{id}")
    public String modifyDeliveryRuleView(@PathVariable("id") Integer id, Model model) {
        DeliveryRuleResponse deliveryRule = deliveryRuleService.getDeliveryRule(id);
        model.addAttribute("id", id);
        model.addAttribute("deliveryRule", deliveryRule);

        return "admin/view/delivery-rule/delivery-rule-modify-view";
    }

    @PostMapping("/modify")
    public String modifyDeliveryRule(@ModelAttribute DeliveryRuleModifyRequest deliveryRuleModifyRequest) {
        if (deliveryRuleService.modifyDeliveryRule(deliveryRuleModifyRequest)) {
            return "redirect:/admin/delivery-rule";
        }
        return "redirect:/admin/delivery-rule/modify";
    }

    @PostMapping("/delete/{id}")
    public String deleteDeliveryRule(@PathVariable("id") Integer id){
        deliveryRuleService.deleteDeliveryRule(id);

        return "redirect:/admin/delivery-rule";
    }
}
