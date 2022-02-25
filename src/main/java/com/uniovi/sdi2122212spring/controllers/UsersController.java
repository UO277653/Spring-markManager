package com.uniovi.sdi2122212spring.controllers;

import com.uniovi.sdi2122212spring.services.RolesService;
import com.uniovi.sdi2122212spring.services.SecurityService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.sdi2122212spring.entities.*;
import com.uniovi.sdi2122212spring.services.UsersService;
import com.uniovi.sdi2122212spring.validators.SignUpFormValidator;

@Controller
public class UsersController {
    @Autowired
    private SignUpFormValidator signUpFormValidator;
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/user/list")
    public String getListado(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list";
    }
    @RequestMapping(value = "/user/add")
    public String getUser(Model model) {

        model.addAttribute("rolesList", rolesService.getRoles());

        model.addAttribute("usersList", usersService.getUsers());
        return "user/add";
    }
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String setUser(@ModelAttribute User user) {
        usersService.addUser(user);
        return "redirect:/user/list";
    }
    @RequestMapping("/user/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("user", usersService.getUser(id));
        return "user/details";
    }
    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersService.deleteUser(id);
        return "redirect:/user/list";
    }
    @RequestMapping(value = "/user/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        User user = usersService.getUser(id);
        model.addAttribute("user", user);
        return "user/edit";
    }
    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
    public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
        // recuperamos el usuario por el dni, findbydni, setNombre,setDNI y setApellido y ese objeto es el que se llama en el update
        User user1 = usersService.getUserByDni(user.getDni());

        user1.setDni(user.getDni());
        user1.setName(user.getName());
        user1.setLastName(user.getLastName());

        //usersService.addUser(user);

        // cambia, llama a updateuser
        usersService.updateUser(user1);

        return "redirect:/user/details/" + id;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {

        signUpFormValidator.validate(user,result);
        if(result.hasErrors()){
            return "signup";
        }

        user.setRole(rolesService.getRoles()[0]);

        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:home";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        User activeUser = usersService.getUserByDni(dni);
        model.addAttribute("markList", activeUser.getMarks());
        return "home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping("/user/list/update")
    public String updateList(Model model){
        model.addAttribute("usersList", usersService.getUsers() );
        return "user/list :: tableUsers";
    }
}
