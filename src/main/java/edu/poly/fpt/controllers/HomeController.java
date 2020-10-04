package edu.poly.fpt.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.fpt.dto.roomDto;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.services.PhongService;
import edu.poly.fpt.services.TaikhoanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PhongService phongService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return "redirect:/home/";
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
