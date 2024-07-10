package com.maven.controller;

import com.maven.entities.Address;
import com.maven.entities.User;
import com.maven.services.AddressService;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.maven.services.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Data
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @RequestMapping( value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, @ModelAttribute("address") Address address) {
        userService.addUser(user);

        address.setUser(user);
        address.setAddressId(user.getId());
        addressService.addAddress(address);
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkUser(@ModelAttribute("user") User user, HttpSession session){
        System.out.println(user.toString());
        User user1 = userService.loginUser(user);
        if(user1!=null){
            session.setAttribute("user",user1);
            return "redirect:/customer";
        }
        if ("admin@gmail.com".equals(user.getEmail()) && "admin".equals(user.getPassword())){
            User adminUser = new User();
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword("admin");
            session.setAttribute("user", adminUser);
            return "redirect:/allUsers";
        }else{
            return "redirect:/login";
        }
    }


}
