package com.crtvu.web;

import com.crtvu.dto.UserItem;
import com.crtvu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Phoenix on 2017/4/10.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getMainPage() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserItem user, HttpSession session) {
        int res = userService.login(user.getUserId(), user.getUserPassword(), user.getUserType());
        if (res != 0) {
            session.setAttribute("userid", user.getUserId());
            switch (res) {
                case 1:
                    return "redirect:/student/"+user.getUserId();
                case 2:
                    return "redirect:/teacher/"+user.getUserId();
                case 3:
                    return "redirect:/admin/"+user.getUserId();
                default:
                    return "redirect:/";
            }
        }
        return "redirect:/";
    }

}
