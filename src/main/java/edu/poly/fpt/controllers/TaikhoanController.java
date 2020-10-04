package edu.poly.fpt.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.fpt.dto.roomDto;
import edu.poly.fpt.dto.userDto;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;

import edu.poly.fpt.services.TaikhoanService;

@Controller
@RequestMapping("/users")

public class TaikhoanController {
	@Autowired
	private TaikhoanService taikhoanService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String add(ModelMap model) {
    	model.addAttribute("userDto", new userDto());
    	return "users/dsTaikhoan";
    }
	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated userDto userDto, BindingResult result
			,RedirectAttributes redire) {
		 if(taikhoanService.existsById(userDto.getTentaikhoan())){
	            model.addAttribute("edit", true);
	        }
		if (result.hasErrors()) {
			model.addAttribute("userDto", userDto);
			return "/users/dsTaikhoan";
		}
		if (!userDto.getMatkhau().equals(userDto.getReMatkhau())) {
			model.addAttribute("userDto", userDto);

			model.addAttribute("err", true);
			return "/users/dsTaikhoan";
		}

		TaiKhoan user = new TaiKhoan();
		user.setTentaikhoan(userDto.getTentaikhoan());
		 user.setMatkhau(passwordEncoder.encode(userDto.getMatkhau()));
		user.setHoten(userDto.getHoten());
		user.setGioitinh(userDto.getGioitinh());
		
		
		user.setSodt(userDto.getSodt());
		user.setEmail(userDto.getEmail());
		user.setRole("USER");
		taikhoanService.save(user);
		model.addAttribute("userDto", userDto);
		System.out.println("==================================" + userDto.getTentaikhoan());
		redire.addFlashAttribute("success", "Saved Users successfully!");
		return "redirect:/users/";
	}

	@GetMapping("edit/{tentaikhoan}")
    public String edit(@PathVariable("tentaikhoan")String tentaikhoan, ModelMap model){
        if(taikhoanService.existsById(tentaikhoan)){
            TaiKhoan user = taikhoanService.findById(tentaikhoan).get();
            userDto userDto = new userDto();
            userDto.setTentaikhoan(user.getTentaikhoan());

			userDto.setHoten(user.getHoten());
			userDto.setGioitinh(user.getGioitinh());
			userDto.setSodt(user.getSodt());
			userDto.setEmail(user.getEmail());
			userDto.setRole(user.getRole());
			
            userDto.setMatkhau("");
            userDto.setReMatkhau("");
            
            
            model.addAttribute("userDto", userDto);
            model.addAttribute("edit", true);
            return "users/dsTaikhoan";
        }
        return "redirect:/users/";
    }

	@GetMapping("delete/{tentaikhoan}")
	public String delete(@PathVariable("tentaikhoan") String username,RedirectAttributes redire) {
		taikhoanService.deleteById(username);
		redire.addFlashAttribute("success", "Deleted Users successfully!");
		return "redirect:/users/";
	}

	@ModelAttribute("users")
	public List<TaiKhoan> getUsers() {
		return (List<TaiKhoan>) taikhoanService.findAll();
	}
	 @PostMapping("find")
	    public String find(@RequestParam( defaultValue = "")String tentaikhoan, ModelMap model){
	        List<TaiKhoan> list = taikhoanService.findByTentaikhoanLikeOrderByTentaikhoan(tentaikhoan);
	        model.addAttribute("userDto", new userDto());
	        model.addAttribute("users", list);
	        System.out.println("======" + list);
	        return "users/dsTaikhoan";
	    }

	@ModelAttribute("attr_user")
    public org.springframework.security.core.userdetails.User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (org.springframework.security.core.userdetails.User) auth.getPrincipal();
    }
}
