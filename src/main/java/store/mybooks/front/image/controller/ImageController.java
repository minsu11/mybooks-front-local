package store.mybooks.front.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import store.mybooks.front.image.adaptor.ImageAdaptor;

/**
 * packageName    : store.mybooks.front.image.controller <br/>
 * fileName       : ImageController<br/>
 * author         : Fiat_lux <br/>
 * date           : 2/28/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/28/24        Fiat_lux       최초 생성<br/>
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageAdaptor imageAdaptor;

    @GetMapping("/admin/book/images")
    public String images() {
        return "admin/view/images";
    }
}
