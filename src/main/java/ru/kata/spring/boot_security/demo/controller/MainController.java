package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class MainController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // загрузка главной страницы
    @GetMapping("/")
    public String welcomeUsers() {
        if (roleService.listRoles().isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
        }
        if (userService.listUsers().isEmpty()) {
            userService.addUser(new User("admin@admin.ru", "100","Администратор", "Компьютерный", "admin@admin.ru", roleService.findRolesByRoleName("ROLE_ADMIN")));
            userService.addUser(new User("user@user.ru", "111","Ivan", "Ivanov", "user@user.ru", roleService.findRolesByRoleName("ROLE_USER")));

        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/index";
    }

    @GetMapping("/new")
    public String newUserPage(){return "newUser";}


    // загрузка личной страницы пользователя
    @GetMapping("/user")
    public String userPage(){return "user";}

    @GetMapping("/admin")
    public String adminPage(){return "admin";}

//    @GetMapping("/new")
//    public String newUserRage(){return "newUser";}
//    @GetMapping("/user/{username}")
//    public String userPage(Model model, @PathVariable("username") String username) {
//        model.addAttribute("user", userService.findByUsername(username));
//        return "user";
//    }
//
//    @GetMapping("/user")
//    public String userPageForUsername(Model model, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        return "user";
//    }

//    @GetMapping("/user/{username}")
////    public String userPage(Model model, @PathVariable("username") String username) {
//    public ModelAndView userPage(Model model, @PathVariable("username") String username) {
//        model.addAttribute("user", userService.findByUsername(username));
//        return new ModelAndView("user");
//    }

//    @GetMapping("/user")
////    public String userPageForUsername(Model model, Principal principal) {
//    public ModelAndView userPageForUsername(Model model, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        model.addAttribute("user", user);
//        return new ModelAndView("user");
//    }

//    @GetMapping("/admin")
//    public String adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {

//    public ModelAndView adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        String username = userDetails.getUsername();
//        model.addAttribute("user", userService.findByUsername(username));
//        model.addAttribute("newUser", new User());
//        model.addAttribute("roles", roleService.listRoles());
//        model.addAttribute("list", userService.listUsers());
//        return new ModelAndView("admin");
//    }
//
//    @GetMapping("/new")
////        public String newUser (Model model){
//
//    public ModelAndView newUser(Model model) {
//        model.addAttribute("newUser", new User());
//        model.addAttribute("roles", roleService.listRoles());
//        return new ModelAndView("newUser");
//    }

//    @PostMapping()
//    public String userCreate(@ModelAttribute("newUser") User user, @RequestParam(value = "role") String role) {
//        user.setRoles(roleService.findRolesByName(role));
//        userService.addUser(user);
//        return "redirect:/admin";
//    }


//    @PostMapping("/update/{id}")
//    public String edit(@ModelAttribute("user") User user) {
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }

    //удаление пользователя
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }


}
