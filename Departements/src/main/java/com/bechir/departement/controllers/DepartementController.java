package com.bechir.departement.controllers;

import java.text.ParseException;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bechir.departement.College;
import com.bechir.departement.Departementss;
import com.bechir.departement.services.DepartementServices;

import jakarta.validation.Valid;

@Controller
public class DepartementController {

    @Autowired
    DepartementServices departementServices;

    @RequestMapping("/ListeDepartement")
    public String listeDepartement(ModelMap modelMap,
            @RequestParam (name="page",defaultValue = "0") int page,
            @RequestParam (name="size", defaultValue = "3") int size) {
            Page<Departementss> depa = departementServices.getAllDepartementParPage(page, size);
            modelMap.addAttribute("Departementss", depa); 
            modelMap.addAttribute("pages", new int[depa.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            return "listeDepartement";
    }

    
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        List<College> col = departementServices.getAllCollege();
        modelMap.addAttribute("Departementss", new Departementss());
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("colleges", col);
        return "formDepartement";
    }

    @PostMapping("/saveDepartement")
    public String saveDepartement(@Valid Departementss Departementss, BindingResult bindingResult, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "3") int size) {
        boolean isNew = false;
        if (bindingResult.hasErrors())
            return "formDepartement";
        if (Departementss.getIdDepartement()==null)
            isNew=true;
        Departementss.setDateAffectation(new Date());
        departementServices.saveDepartement(Departementss);
        if (isNew) {
            Page<Departementss> depar = departementServices.getAllDepartementParPage(page, size);
            page = depar.getTotalPages()-1;
        }
        return ("redirect:/ListeDepartement?page="+page+"&size="+size);
    }






    @RequestMapping("/supprimerDepartement")
    public String supprimerDepartement(@RequestParam("id") Long id, @RequestParam(name="page", defaultValue = "3") int page,
                                       @RequestParam(name="size", defaultValue = "3") int size , ModelMap modelMap) {
        departementServices.deleteDepartementById(id);
        Page<Departementss> depa = departementServices.getAllDepartementParPage(page, size);
        modelMap.addAttribute("Departementss", depa); 
        modelMap.addAttribute("pages", new int[depa.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ListeDepartement"; 
    }

    @RequestMapping("/modifierDepartement")
    public String editerDepartement(@RequestParam("id") Long id, ModelMap modelMap) {
        Departementss p = departementServices.getDepartement(id);
        List<College> col = departementServices.getAllCollege();
        modelMap.addAttribute("Departementss", p);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("colleges", col);

        return "formDepartement";
    }

    @RequestMapping("/updateDepartement")
    public String updateDepartement(@ModelAttribute("Departementss") Departementss departement, @RequestParam("date") String date, ModelMap modelMap) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAffectation = dateFormat.parse(date);
        departement.setDateAffectation(dateAffectation);
        departementServices.updateDepartement(departement);

        List<Departementss> depa = departementServices.getAllDepartement();
        modelMap.addAttribute("departements", depa);
        return "listeDepartement";
        
        
        
    }
    
    @GetMapping(value = "/")
    public String welcome() {
    return "index";
    }
        
    
    

    }



