package store.mybooks.front.admin.delivery_rule.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.mybooks.front.admin.delivery_rule.adaptor.DeliveryRuleAdaptor;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleModifyRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleRegisterRequest;
import store.mybooks.front.admin.delivery_rule.dto.DeliveryRuleResponse;

/**
 * packageName    : store.mybooks.front.admin.delivery_rule.service <br/>
 * fileName       : DeliveryRuleService<br/>
 * author         : Fiat_lux <br/>
 * date           : 3/11/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 3/11/24        Fiat_lux       최초 생성<br/>
 */
@Service
@RequiredArgsConstructor
public class DeliveryRuleService {
    private final DeliveryRuleAdaptor deliveryRuleAdaptor;

    public List<DeliveryRuleResponse> getAllDeliveryRule() {
        return deliveryRuleAdaptor.getAllDeliveryRule();
    }

    public DeliveryRuleResponse getDeliveryRule(Integer id) {
        return deliveryRuleAdaptor.getDeliveryRule(id);
    }

    public boolean createDeliveryRule(DeliveryRuleRegisterRequest deliveryRuleRegisterRequest) {
        try {
            deliveryRuleAdaptor.createDeliveryRule(deliveryRuleRegisterRequest);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean modifyDeliveryRule(DeliveryRuleModifyRequest deliveryRuleModifyRequest) {
        try {
            deliveryRuleAdaptor.modifyDeliveryRule(deliveryRuleModifyRequest);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean deleteDeliveryRule(Long id) {
        try {
            deliveryRuleAdaptor.deleteDeliveryRule(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
