/**
 * Created by slipkinem on 8/25/2017 at 10:02 AM.
 */

package top.slipkinem.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Html5ModeController {
    @RequestMapping(value = {"", "/", "/{[path:(?!static).*}/**"}, method = RequestMethod.GET)
    public String indexAction() {
        return "index";
    }
}
