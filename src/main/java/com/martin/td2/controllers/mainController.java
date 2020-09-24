package com.martin.td2.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.martin.td2.models.Groupe;
import com.martin.td2.models.Organization;
import com.martin.td2.models.User;
import com.martin.td2.repositories.GroupeRepository;
import com.martin.td2.repositories.OrgaRepository;
import com.martin.td2.repositories.UserRepository;

@Controller
@RequestMapping("/orga")
public class mainController {
	@Autowired
	private OrgaRepository orgaRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private GroupeRepository groupeRepo;
	
	@GetMapping(value={"","/","index"})
	public String index(Model model, @Param("keyword") String keyword) {
		if (keyword != null) {
			List<Organization> organizations = orgaRepo.search(keyword);
			model.addAttribute("organizations", organizations);
			return "index";
		}
		List<Organization> organizations = orgaRepo.findAll();
		List<User> users = userRepo.findAll();
		model.addAttribute("organizations", organizations);
		model.addAttribute("users", users);
		return "index";
	}
	
	@GetMapping("new")
	public String orgaNew() {
		return ("newOrga");
	}
	
	@PostMapping("addNew/")
	public RedirectView addOrga(@RequestParam String name,@RequestParam String domain,@RequestParam String aliases) {
		Organization orga = new Organization(name,domain,aliases);
		orgaRepo.saveAndFlush(orga);
		return new RedirectView("/orga/");
	}
	
	@GetMapping("findbyid/{id}")
	public @ResponseBody String getOrga(@PathVariable int id) {
		Organization organizations = orgaRepo.findById(id);
		if(organizations != null) {
			return organizations.toString();
		}
		return "Organisation non trouvée";
	}
	@GetMapping("newuser/{userName}/{orgaName}")
	public @ResponseBody String addUserInOrga(@PathVariable String userName,@PathVariable String orgaName) {
		Optional<Organization> optional = orgaRepo.findByName(orgaName);
		if(optional.isPresent()) {
			User user = new User();
			user.setFirstName(userName);
			user.setOrganization(optional.get());
			userRepo.saveAndFlush(user);
			return user + " ajoutée !";
		}
		return "Organization " + orgaName + " don't exist !";
	}
	
	@GetMapping("display/{id}")
    public String display(ModelMap model,@PathVariable int id) {
		Organization organization = orgaRepo.findById(id);
		model.put("organization", organization);
		return "displayOrga";
	}
	
	@GetMapping("edit/{id}")
	public String edit(ModelMap model,@PathVariable int id) {
		Organization organization = orgaRepo.findById(id);
		model.put("organization", organization);
		return "editOrga";
	}
	
	@PostMapping("editOrga/")
	public RedirectView editOrga(@RequestParam int id,@RequestParam String name,@RequestParam String domain,@RequestParam String aliases) {
		Organization organization = orgaRepo.findById(id);
		if((organization != null)) {
			organization.setName(name);
			organization.setDomain(domain);
			organization.setAliases(aliases);
			orgaRepo.saveAndFlush(organization);
		}
		return new RedirectView("../");
	}
	
	@Transactional
	@GetMapping("delete/{id}")
	public RedirectView delete(@PathVariable int id) {
		Organization organization = orgaRepo.findById(id);
		if((organization != null)) {
			List<User> users = userRepo.findByOrganization(organization);
			if(users.size()>0)
			{
				userRepo.updateUserSetOrganization(organization);
			}
			List<Groupe> groupes = groupeRepo.findByOrganization(organization);
			if(groupes.size()>0)
			{
				groupeRepo.updateGroupeSetOrganization(organization);
			}
			orgaRepo.deleteById(id);
		}
		return new RedirectView("../");
	}
}