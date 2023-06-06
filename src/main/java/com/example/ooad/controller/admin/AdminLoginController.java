package com.example.ooad.controller.admin;

import com.example.ooad.jpa.entity.AdminUser;
import com.example.ooad.jpa.AdminUserRepository;
import com.example.ooad.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.ooad.controller.Constants.USERNAME_ATTRIBUTE_NAME;

@Controller
public class AdminLoginController {
    @Autowired
    private AdminUserRepository adminUserRepository; // creating object of the required repository

    /**
     * This function is used to load the Admin login page at /admin_login.
     * @param model is used to pass data back to view/html file.
     * @return name of the view or html file which should be returned to browser.
     */
    @GetMapping("/admin_login")
    public String getAdminLoginPage(Model model) {

        return "admin/admin_login"; //name of view/html page to be loaded
    }

    /**
     * This function is used to handle login request from admin login page.
     * When user enters username, password, that will be saved in @link{UserModel} class
     * and passed to controller here. Then we check database for this user and verify password
     * is correct.
     * If login is successful, we return admin_home view name and add userName to @link{Model} object.
     * If login fails, we return admin_login page but with a message in @link{Model} object.
     * @param userModel input from View is saved here.
     * @param model is used to return data back to View.
     * @return name of the view or html file to be used post login.
     */
    @PostMapping("/admin_login")
    public String submitAdminLogin(@ModelAttribute UserModel userModel,
                                   Model model,
                                   HttpSession httpSession) {
        System.out.println(userModel.getUsername());
        //The below call finds this user by userName in database and returns AdminUser entity object.
        AdminUser adminUser = adminUserRepository.findByUserName(userModel.getUsername());
        if (adminUser == null ) {
            System.out.println("No user found");
        }
        if (adminUser!= null && adminUser.getPassword().equals(userModel.getPassword())) {
            //Login successful, returning admin_home page with username variable set in the Model.
            httpSession.setAttribute(USERNAME_ATTRIBUTE_NAME, adminUser.getUserName());
            return "redirect:admin_home";
        }
        //Login failed, returning admin_login page with message variable set in Model.
        model.addAttribute("message", "Incorrect username or password.");
        return "admin/admin_login";
    }

    @GetMapping("/admin_logout")
    public String logoutAdmin(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:admin_login";
    }

}
