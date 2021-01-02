package com.stargate.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stargate.demo.dao.ModuleTbl;
import com.stargate.demo.dao.ModuleRepository;

@Controller
public class FormController {
	@Autowired
	ModuleRepository moduleRepo;

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showForm(Model model) {
//    	long id=1;
//    	Optional<ModuleTbl> moduleRec=moduleRepo.findById(id);
    	ModuleTbl module=new ModuleTbl();
//    	
//    	int row=Integer.parseInt(module.getFormOutput());
    	List<String> textBoxes=new ArrayList<String>();
//    	for(int a=0;a<row;a++) {
//    		textBoxes.add("Text box "+(a+1));
//    	}    	
    	model.addAttribute("moduleTbl", module);
    	model.addAttribute("textBoxes", textBoxes);
    	
		return "dynamicForm";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submit(@ModelAttribute("moduleTbl") ModuleTbl module, BindingResult result, ModelMap model) {
    	int row=0;
    	try {
	    	row=Integer.parseInt(moduleRepo.getFormOutputFromDb(module.getModule(), module.getMajorType(), module.getMainType()));
	    }catch(Exception ex) {
	    	row=0;
	    }

    	List<String> textBoxes=new ArrayList<String>();
    	for(int a=0;a<row;a++) {
    		textBoxes.add("Text box "+(a+1));
    	}    	
    	model.addAttribute("textBoxes", textBoxes);

		return "dynamicForm";

    }
}
