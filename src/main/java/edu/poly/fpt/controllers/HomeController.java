package edu.poly.fpt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.fpt.config.MailConstructor;
import edu.poly.fpt.config.SecurityUtility;
import edu.poly.fpt.dto.roomDto;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.services.PhongService;
import edu.poly.fpt.services.TaikhoanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class HomeController {

	@Autowired
	private PhongService phongService;
    
    @Autowired
	private TaikhoanService taikhoanService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private JavaMailSender mailSender;

    private static final int BUTTONS_TO_SHOW = 15;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	
	
    
    @GetMapping(value = {"/", "/login"})
   	public ModelAndView homeindex(@RequestParam("page") Optional<Integer> page) {
   		return getStaffsAndPage(new roomDto(), page);
   	}

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
    @RequestMapping("/forgetPassword")
	public String forgetPassword(HttpServletRequest request, @ModelAttribute("email") String email,Model model,
			@RequestParam("page") Optional<Integer> page) {

		model.addAttribute("classActiveForgetPassword", true);

		TaiKhoan user = taikhoanService.findByEmail(email);

		if (user == null) {
			int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
			Page<Phong> roomList = phongService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
			PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
			model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
			model.addAttribute("roomDto", new roomDto());
			model.addAttribute("phong", roomList);
			model.addAttribute("pager", pager);
			model.addAttribute("emailNotExist", true);

			return "customer/index";
		}
		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setMatkhau(encryptedPassword);

		taikhoanService.save(user);

		String token = UUID.randomUUID().toString();
		taikhoanService.createPasswordResetTokenForUser(user, token);

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user,
				password);

		mailSender.send(newEmail);
		model.addAttribute("forgetPasswordEmailSent", true);

		return "redirect:/";
	}
    @RequestMapping("/myProfile")
	public String myProfile(Model model, Principal principal,@RequestParam("page") Optional<Integer> page) {
    	TaiKhoan user = taikhoanService.findByTentaikhoan(principal.getName());
		
		model.addAttribute("user", user);
		
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<Phong> roomList = phongService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("roomDto", new roomDto());
		model.addAttribute("phong", roomList);
		model.addAttribute("pager", pager);
		
		model.addAttribute("classActiveEdit", true);
		return "customer/index";
	}

    @ModelAttribute("attr_user")
    public org.springframework.security.core.userdetails.User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return (User) auth.getPrincipal();
        }
        return null;
    }
   
	public ModelAndView getStaffsAndPage(roomDto roomDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("customer/index");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<Phong> roomList = phongService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("roomDto", roomDto);
		modelAndView.addObject("phong", roomList);
		modelAndView.addObject("selectedPageSize", INITIAL_PAGE_SIZE);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}
    @ModelAttribute("indexActive")
    public String getActive(){
        return "active";
    }
}
