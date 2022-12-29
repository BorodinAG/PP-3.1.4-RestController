package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainRestController {

    private final UserService userService;
    private final RoleService roleService;
    final
    RestTemplate restTemplate;

    @Autowired
    public MainRestController(UserService userService, RoleService roleService, RestTemplate restTemplate) {
        this.userService = userService;
        this.roleService = roleService;
        this.restTemplate = restTemplate;
    }

    // страничка пользователя
    @GetMapping("/user")
    public User userPageForUsername(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    // все пользователи
    @GetMapping("/admin")
    public List<User> getAllUsers() {
        return userService.listUsers();
    }

    //проверка
    @GetMapping("/employee")
    public List<User> getAllEmployee() {
        return userService.listUsers();
    }
    // один пользователь
    @GetMapping("/admin/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    //создание пользователя
    @PostMapping("/admin")
    public List<User> createNewUser(@RequestBody User user) {
        userService.addUser(user);
        return userService.listUsers();
    }

    //создание пользователя
    @PostMapping("/new")
    public List<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return userService.listUsers();
    }
    // обновление пользователя
    @PutMapping("/admin")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    // удаление пользователя
    @DeleteMapping("/admin/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return userService.listUsers();
    }

    @GetMapping("/admin/roles")
    public List<Role> getRoles() {
        return roleService.listRoles();
    }

//    @GetMapping()
//    public String getAllUsers() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        return restTemplate.exchange("http://localhost:8080/users", HttpMethod.GET,entity,String.class).getBody();
//    }

//    @PostMapping()
//    public String newUser(@RequestBody User user) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//
//        return restTemplate.exchange("http://localhost:8080/new", HttpMethod.POST, entity, String.class).getBody();
//    }
//
//    @PutMapping("/update/{id}")
//    public String userEdit(@RequestBody User user, @PathVariable("id") String id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//
//        return restTemplate.exchange("http://localhost:8080/update/" + id, HttpMethod.PUT, entity, String.class).getBody();
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") String id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<User> entity = new HttpEntity<>(headers);
//
//        return restTemplate.exchange("http://localhost:8080/delete/" + id, HttpMethod.DELETE, entity, String.class).getBody();
//    }

}
