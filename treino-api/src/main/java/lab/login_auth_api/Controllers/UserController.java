package lab.login_auth_api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.login_auth_api.service.UserService;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
}
