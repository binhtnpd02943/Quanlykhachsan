package edu.poly.fpt.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Lists;

import edu.poly.fpt.dto.cityDto;
import edu.poly.fpt.dto.datphongDto;
import edu.poly.fpt.dto.typeDto;
import edu.poly.fpt.dto.userDto;
import edu.poly.fpt.entities.DatPhong;
import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.entities.ThanhPho;
import edu.poly.fpt.repositories.DichvuRepository;
import edu.poly.fpt.services.DatphongService;
import edu.poly.fpt.services.DichvuService;
import edu.poly.fpt.services.KhachsanService;
import edu.poly.fpt.services.LoaikhachsanService;
import edu.poly.fpt.services.PhongService;
import edu.poly.fpt.services.ThanhphoService;

@Controller
@RequestMapping("/view/")
public class DatphongController {
	@Autowired
private PhongService phongService;
	
	@Autowired 
	private DatphongService datphongService;
	@GetMapping("/booking-form")
    public String add(ModelMap model) {
    	model.addAttribute("datphongDto", new datphongDto());
		return "customer/booking-form";
		
    }
	
	@PostMapping("saveOrUpdate/{id}")
	public String saveOrUpdate(ModelMap model, @Validated datphongDto datphongDto, BindingResult result
			,RedirectAttributes redire,Principal principal,@PathVariable("id") Integer id) {
				// if (result.hasErrors()) {
				// 	model.addAttribute("message", "vui long nhap tat ca cac du lieu!!");
				// 	model.addAttribute("datphongDto", datphongDto);
				// 	return "/customer/booking-form";
				// }
		
		DatPhong dp = new DatPhong();
		
		dp.setDahuy(false);
		dp.setNgaydat(new Date());
		dp.setNgayden(datphongDto.getNgayden());
		dp.setNgaytra(datphongDto.getNgaytra());
		dp.setNguoilon(datphongDto.getNguoilon());
		dp.setTrecon(datphongDto.getTrecon());
		dp.setDichvu(datphongDto.getDichvu());
		dp.setThanhtien(Float.parseFloat(datphongDto.getThanhtien()+"")) ;
		dp.setGhichu(datphongDto.getGhichu());
		dp.setSophong(datphongDto.getSophong());
		dp.setCmt(datphongDto.getCmt());
		dp.setTen(datphongDto.getTen());
		Phong phong = phongService.findById(id).get();
		dp.setPhong(phong);
		TaiKhoan tk = new TaiKhoan();
		tk.setTentaikhoan(principal.getName());
		dp.setTaikhoan(tk);
		datphongService.save(dp);		
		model.addAttribute("datphongDto",new datphongDto());
		model.addAttribute("success","dat thanh cong");
		System.out.println("==================================" + datphongDto.getId());
		
		
		// return "redirect:/view/booking-form";
		return "redirect:/";
	}
	

	@GetMapping("/gallery")
	public String gallery() {
		return "customer/gallery";
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
