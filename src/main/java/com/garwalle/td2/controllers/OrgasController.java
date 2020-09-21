package com.garwalle.td2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.garwalle.td2.repositories.OrgaRepository;
import com.garwalle.td2.models.Organization;

@Controller
@SessionAttributes("organizations")
@RequestMapping("/orgas/")
public class OrgasController {
	
	@ModelAttribute("organizations")
	public List<Organization> getOrganizations() {
		return new ArrayList<Organization>();
	}

	@Autowired
	private OrgaRepository repo;

	@RequestMapping("")
	public String index(@ModelAttribute("organizations") List<Organization> organizations) {
		return "index";
	}

	@RequestMapping("newOrga")
	public String NewOrga() {
		return "newOrga";
	}

	@PostMapping("addNewOrga")
	@ResponseBody
	public RedirectView addNewOrga(@RequestParam String nom, @RequestParam String domain, @RequestParam String aliases,@ModelAttribute("organizations") List<Organization> organizations) {
		Organization orga = new Organization(nom, domain, aliases);
		organizations.add(orga);
		return new RedirectView("");
	}
}
