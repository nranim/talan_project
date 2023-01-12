package com.ensat.controllers;

import com.ensat.entities.User;
import com.ensat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@RestController("/Users")
public class UserContoller {
     @Autowired
     private UserService userService;

   


   @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("Users", userService.listAllUsers());
        System.out.println("Returning Users:");
        return "Users";
    }


    @GetMapping("/{id}")
    public String showUser(@PathVariable Integer id, Model model) {
        model.addAttribute("User", userService.getUserById(id));
        return "Usershow";
    }

    // Afficher le formulaire de modification du User
    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("User", userService.getUserById(id));
        return "Userform";
    }


    @RequestMapping("User/new")
    public String newUser(Model model) {
        model.addAttribute("User", new User());
        return "Userform";
    }


    @RequestMapping(value = "User", method = RequestMethod.POST)
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/User/" + user.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/Users";
    }

}
