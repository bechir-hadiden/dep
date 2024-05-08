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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bechir.departement.College;
import com.bechir.departement.Departementss;
import com.bechir.departement.services.CollegeServices;
@Controller
public class CollegeController {
	
	@Autowired	
	CollegeServices collegeServices ; 
	

    @RequestMapping("/listCollege")
    public String listeCollege(ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,@RequestParam (name="size", defaultValue = "3") int size) {
            Page<College> depa = collegeServices.getAllCollegeParPage(page, size);
            modelMap.addAttribute("College", depa); 
            modelMap.addAttribute("pages", new int[depa.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            return "listCollege";
    }
    
    
    @RequestMapping("/showCreatecollege")
    public String showCreatecollege() {
        return "CreateCollege";
    }


    @RequestMapping("/supprimerCollege")
    public String supprimerCollege(@RequestParam("id") Long id, @RequestParam(name="page", defaultValue = "3") int page,@RequestParam(name="size", defaultValue = "3") int size , ModelMap modelMap) {
    	
        collegeServices.deleteCollegeById(id);
        Page<College> col = collegeServices.getAllCollegeParPage(page, size);
        modelMap.addAttribute("College", col); 
        modelMap.addAttribute("pages", new int[col.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listCollege"; 
    }
    

    
    @RequestMapping("/saveCollege")
    public String saveDepartement(@ModelAttribute("College") College college, BindingResult bindingResult,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        collegeServices.saveCollege(college);
        Page<College> depar = collegeServices.getAllCollegeParPage(page, size);
        page = depar.getTotalPages()-1;
        return "listCollege";
    }

    
    @RequestMapping("/modifierCollege")
    public String editerCollege(@RequestParam("id") Long id, ModelMap modelMap) {
        College p = collegeServices.getCollege(id);
        modelMap.addAttribute("College", p);
        return "editCollege";
    }

    @RequestMapping("/updateCollege")
    public String updateCollege(@ModelAttribute("College") College college, ModelMap modelMap, @RequestParam("idcol") Long idcol, @RequestParam(name="page", defaultValue = "3") int page, @RequestParam(name="size", defaultValue = "3") int size ) throws ParseException {

        collegeServices.updateCollege(college);
        List<College> col = collegeServices.getAllCollege();
        modelMap.addAttribute("College", col);
        return "listCollege";

}
    
    
   
}