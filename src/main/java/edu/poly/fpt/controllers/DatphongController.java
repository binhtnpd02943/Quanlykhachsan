package edu.poly.fpt.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.fpt.dto.cityDto;
import edu.poly.fpt.dto.typeDto;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.ThanhPho;
import edu.poly.fpt.services.LoaikhachsanService;
import edu.poly.fpt.services.PhongService;
import edu.poly.fpt.services.ThanhphoService;

@Controller
@RequestMapping("/view")
public class DatphongController {
	
	@GetMapping("/gallery")
	public String gallery() {
		return "customer/gallery";
	}
	@GetMapping("/services")
	public String service() {
		return "customer/services";
	}
	
	@GetMapping("/packages-detail")
	public String detail() {
		return "customer/packages-detail";
	}
	@GetMapping("/service-detail")
	public String details() {
		return "customer/service-detail";
	}
	@GetMapping("/all-flights")
	public String flights() {
		return "customer/all-flights";
	}
	@GetMapping("/flight-packages-detail")
	public String flight() {
		return "customer/flight-packages-detail";
	}
	@GetMapping("/booking-form")
	public String booking() {
		return "customer/booking-form";
	}
	@GetMapping("/dinnings")
	public String dinning() {
		return "customer/dinnings";
	}
	@GetMapping("/luxury-tours")
	public String luxury() {
		return "customer/luxury-tours";
	}
	@GetMapping("/blog-grid")
	public String blog() {
		return "customer/blog-grid";
	}
	@GetMapping("/index3")
	public String index() {
		return "customer/index3";
	}
	
	@ModelAttribute("attr_user")
    public org.springframework.security.core.userdetails.User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return (User) auth.getPrincipal();
        }
        return null;
    }
}
