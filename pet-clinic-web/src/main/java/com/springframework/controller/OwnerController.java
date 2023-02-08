package com.springframework.controller;

import org.springframework.ui.Model;
import com.springframework.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }



    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());

        return "/owners/index.html";
    }

    @RequestMapping("/find")
    public String findOwners(){
        return "notimplemented";
    }

    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/ownerDetails";
    }
}
