package com.maven.controller;

import com.maven.entities.Address;
import com.maven.entities.User;
import com.maven.services.AddressService;
import com.maven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/customer")
    public String customer(){
        return "customer";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @RequestMapping("/allUsers")
    public String getAllUsers(@ModelAttribute("user") User user, HttpSession session){
        List<User> allUser = userService.getAllUser();
        session.setAttribute("user", allUser);
        return "dashboard";
    }

    @RequestMapping("/editProfile/{id}")
    public String editProfile(@PathVariable("id") Long id, HttpSession session){
        User user = userService.getUser(id);
        session.setAttribute("user",user);
        Address address = addressService.getAddress(id);
        session.setAttribute("address", address);
        System.out.println(user);
        return "editProfile";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("userCity") String userCity,
                             @RequestParam("userState") String userState,
                             @RequestParam("userCountry") String userCountry,
                             @RequestParam("userPincode") String userPincode,
                             @RequestParam("profileImage") MultipartFile file,
                             HttpSession session) throws IOException {

        // Directory where images will be saved
        String imagePath = "E:/spring Web/Practical01/src/main/webapp/WEB-INF/views/resources/image/";

        // Check if the directory exists, create if not
        File directory = new File(imagePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the file to the specified path
        String fileName = file.getOriginalFilename();
        String filePath = imagePath + fileName;
        File destinationFile = new File(filePath);
        file.transferTo(destinationFile);

        userService.updateUser(user);

        Address address = new Address();
        address.setUser(user);
        address.setAddressId(user.getId());
        address.setUserCity(userCity);
        address.setUserState(userState);
        address.setUserCountry(userCountry);
        address.setUserPincode(userPincode);
        address.setProfileImage(fileName);

        addressService.update1Address(address);

        User user1 = userService.getUser(user.getId());
        session.setAttribute("user", user1);

        return "customer";
    }

    @RequestMapping(value = "/editUser")
    public String editUser(@ModelAttribute("user") User user,@ModelAttribute("address") Address address,@RequestParam(value = "profileImage", required = false) MultipartFile file, HttpSession session) throws IOException {

        String imagePath = "E:/spring Web/Practical01/src/main/webapp/WEB-INF/views/resources/image/";

        // Check if the directory exists, create if not
        File directory = new File(imagePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the file to the specified path if a file was uploaded
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = imagePath + fileName;
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);
            address.setProfileImage(fileName); // Set the file name only if a file was uploaded
            System.out.println("Uploaded file name: " + fileName);
        }

        userService.update1User(user);
        address.setUser(user);
        address.setAddressId(user.getId());
        addressService.update1Address(address);
        List<User> allUser = userService.getAllUser();
        session.setAttribute("user", allUser);
        return "customersList";
    }
    @RequestMapping(value = "/delete")
    public String deleteUser(@ModelAttribute("user") User user, HttpSession session){
        userService.deleteUser(user);
        List<User> allUser = userService.getAllUser();
        session.setAttribute("user",allUser);
        return "customersList";
    }

}
