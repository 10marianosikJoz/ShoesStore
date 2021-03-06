package webapplication.ShoesShopApp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Role;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.model.dto.EditUserStatusDto;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.role.RoleServiceImpl;
import webapplication.ShoesShopApp.service.user.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Autowired
    private RoleServiceImpl roleServiceImpl;



    @RequestMapping("/login")
    public String login (){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @GetMapping("/privileges")
    public String privileges(Model model){
        List<User> userList = userServiceImpl.listAll();
        model.addAttribute("userList", userList);
        return "privileges";
    }

    @GetMapping("/deleteUser/{id}")
    public String delUser(@PathVariable(name = "id") int id){
        userServiceImpl.delete(id);
        return "redirect:/privileges";
    }

    @GetMapping("/changeUserStatus/{id}")
    public String changeUserStatus(
            @PathVariable(name = "id") long id,
            @Valid @ModelAttribute("userStatus") EditUserStatusDto editUserStatusDto,
            BindingResult result){
        userServiceImpl.changeUserStatus(id, editUserStatusDto);
        return "redirect:/privileges";
    }


    @GetMapping("/editUserStatus")
    public String editUserStatus(Model model){
        List<User> userList = userServiceImpl.listAll();
        model.addAttribute("userList",userList);
        return "editUserStatus";
    }

    @GetMapping("/editUserStatus/{id}")
    public ModelAndView editUserStatus(@PathVariable(name = "id") Long id, Model model){
        ModelAndView modelAndView = new ModelAndView("editUserStatus");
        User user = userServiceImpl.getUserById(id);
        List<Role> roles = roleServiceImpl.getAllRoles();
        modelAndView.addObject("user",user);
        model.addAttribute("roles", roles);
        return modelAndView;
    }

    @GetMapping("/returnsAndExchanges")
    public String returnsEndExchanges() {
        return "returnsAndExchanges";
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @GetMapping("/dataProtection")
    public String dataProtection() {
        return "dataProtection";
    }

    @GetMapping("/terms")
    public String terms() { return "terms";
    }

    @GetMapping("/about")
    public String about() { return "about";
    }

    @GetMapping("/history")
    public String history() { return "history";
    }

    @GetMapping("/department")
    public String department() { return "markDepartment";
    }

    @GetMapping("/cart")
    public String shoppingCart() {
        return "shoppingCart";
    }



}
