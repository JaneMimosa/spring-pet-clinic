package com.springframework.controller;

import com.springframework.model.Owner;
import com.springframework.model.Pet;
import com.springframework.model.PetType;
import com.springframework.repositories.OwnerRepository;
import com.springframework.services.OwnerService;
import com.springframework.services.PetService;
import com.springframework.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.Collection;


@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final OwnerRepository ownerRepository;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService,
                         OwnerRepository ownerRepository) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.ownerRepository = ownerRepository;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("pets/new")
    public String initCreationForm(Owner owner, Model model){
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/new")
    public String processCreationForm(@Valid Pet pet, Owner owner, BindingResult result, Model model) {
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && (owner.getPet(pet.getName(), true) != null)){
            result.rejectValue("name", "duplicate", "already exists");
        }
        pet.setOwner(owner);
        owner.addPet(pet);
        if(result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model){
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if(result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
           pet.setOwner(owner);
           petService.save(pet);
           return "redirect:/owners/" + owner.getId();
        }
    }
}
