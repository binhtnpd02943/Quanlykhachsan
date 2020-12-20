package edu.poly.fpt.controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
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
import edu.poly.fpt.services.TaikhoanService;
import edu.poly.fpt.services.ThanhphoService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.micrometer.core.instrument.MeterRegistry.Config;

@Controller
@RequestMapping("/view/")
public class DatphongController {
	
	
	@Autowired
	private Configuration config;
	@Autowired
    public JavaMailSender emailSender;
	@Autowired
	private Environment env;
	@Autowired
private PhongService phongService;
	@Autowired
	private TaikhoanService taikhoanService;	

	@Autowired 
	private DatphongService datphongService;
	
	
	@GetMapping("/booking-form")
    public String add(ModelMap model) {
		return "customer/booking-form";
		
    }
	
	@PostMapping("saveOrUpdate/{id}")
	public String saveOrUpdate(ModelMap model, @Validated datphongDto datphongDto, BindingResult result,HttpSession session,Model ml
			,RedirectAttributes redire,final Principal principal,@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
				if(null == principal){
					System.out.println("chuwa dang nhap");
					redirectAttributes.addFlashAttribute("message", "you need to login if you want to book room");
					return "redirect:/view/bookroom/" +id;
				}
				// if (result.hasErrors()) {
				// 	// ml.addAttribute("message", "vui long nhap tat ca cac du lieu!!");
				// 	redirectAttributes.addFlashAttribute("datphongDto", datphongDto);
				// 	redirectAttributes.addFlashAttribute("message", "please fill all the form!");
				// 	// model.addAttribute("datphongDto", datphongDto);
				// 	return "redirect:/view/bookroom/{id}";
				// }
	
		
		
		if (datphongDto.getNgaytra().before(datphongDto.getNgayden())){
			redirectAttributes.addFlashAttribute("message", "date check in is greater date check out!");
			return "redirect:/view/bookroom/" +id;
		}
		if (datphongDto.getNgayden().before(new Date())){
			redirectAttributes.addFlashAttribute("message", "date check in is smaller todate !");
			return "redirect:/view/bookroom/" +id;
		}
		LocalDate startDate = LocalDate.ofInstant(datphongDto.getNgayden().toInstant(), ZoneId.systemDefault()); 
			
		LocalDate endDate = LocalDate.ofInstant(datphongDto.getNgaytra().toInstant(), ZoneId.systemDefault());
 
		List<LocalDate> listOfDates = startDate.datesUntil(endDate).collect(Collectors.toList());
		Phong phong = phongService.findById(id).get();
		for(int i = 0 ; i<listOfDates.size();i++){
			Date date = java.sql.Date.valueOf(listOfDates.get(i));
			Float a =datphongService.soLuongPhong(id, date );
			if(a != null ){
				if (phong.getSoluong() <= a || phong.getSoluong() < (a + datphongDto.getSophong() )) {
					
					redirectAttributes.addFlashAttribute("message", "the room you book is full !");
					return "redirect:/view/bookroom/" +id;
				}
			}
		}	
 
		DatPhong dp = new DatPhong();
		
		dp.setDahuy(false);
		dp.setNgaydat(new Date());
		dp.setNgayden(datphongDto.getNgayden());
		dp.setNgaytra(datphongDto.getNgaytra());
		dp.setNguoilon(datphongDto.getNguoilon());
		dp.setTrecon(datphongDto.getTrecon());
		dp.setDichvu(datphongDto.getDichvu());
		dp.setThanhtien(Float.parseFloat(phong.getGiathue()*datphongDto.getSophong()+"")) ;
		dp.setGhichu(datphongDto.getGhichu());
		dp.setSophong(datphongDto.getSophong());
		dp.setCmt(datphongDto.getCmt());
		dp.setTen(datphongDto.getTen());
		
		dp.setPhong(phong);
		TaiKhoan tk = taikhoanService.findByTentaikhoan(principal.getName());
		
		dp.setTaikhoan(tk);
		
		// Template t = config.getTemplate("email.ftl");
		// String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, data);
		
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(tk.getEmail());
		email.setSubject("Hotel Booking Manager - Booking Success");
		email.setText("adsad");
		email.setFrom(env.getProperty("support.email"));
		this.emailSender.send(email);
		
		datphongService.save(dp);		
		
		model.addAttribute("flag","showAlert");
		model.addAttribute("datphongDto",new datphongDto());
		model.addAttribute("success","dat thanh cong");
		System.out.println("==================================" + datphongDto.getId());
		redire.addFlashAttribute("success", "Saved Services successfully!");
		
		
		
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
	@GetMapping("/404")
	public String notfound() {
		return "customer/404";
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
