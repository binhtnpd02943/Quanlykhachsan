package edu.poly.fpt.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.fpt.dto.ratingDto;
import edu.poly.fpt.entities.DanhGia;
import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.TaiKhoan;

import edu.poly.fpt.services.DanhgiaService;
import edu.poly.fpt.services.KhachsanService;
import edu.poly.fpt.services.TaikhoanService;

@Controller
@RequestMapping("/rating")
public class DanhgiaController {
	@Autowired
	private DanhgiaService danhgiaService;
	@Autowired
	private KhachsanService khachsanService;

	@RequestMapping(value = "/newRating/{id}", method = RequestMethod.POST)
	public String saveOrUpdate(ModelMap model, @Validated ratingDto ratingDto, BindingResult result,
			RedirectAttributes redire,Principal principal,@PathVariable("id") Long id) {
	
		 if (result.hasErrors()) {
		 	model.addAttribute("message", "vui long nhap tat ca cac du lieu!!");
		 	model.addAttribute("ratingDto", ratingDto);
		 	return "redirect:/view/profile/{id}";
		 }

		// add new
		if (ratingDto.getId() != null && ratingDto.getId() > 0) {
		}
		
		if (ratingDto.getTaikhoan() != null) {
			model.addAttribute("ratingDto", ratingDto);

			model.addAttribute("err", true);
			return "redirect:/view/profile/{id}";
		}

		DanhGia rating = new DanhGia();

		rating.setId(ratingDto.getId());
		rating.setDanhgia(ratingDto.getDanhgia());
		rating.setNgay(new Date());
		rating.setNoidung(ratingDto.getNoidung());
		TaiKhoan tk = new TaiKhoan();
		tk.setTentaikhoan(principal.getName());
		rating.setTaikhoan(tk);

		System.out.println("id ============================" +id);
		KhachSan ks = khachsanService.findById(id).get(); 
		rating.setKhachsan(ks);
		
		danhgiaService.save(rating);
		model.addAttribute("ratingDto", ratingDto);
		
		System.out.println("==================================" + ratingDto.getId());
		redire.addFlashAttribute("success", "Saved Rating successfully!");
		return "redirect:/view/profile/{id}";
	}
	
//	 @ModelAttribute("resultRating")
//	    public List<Object[]> getListDanhgia(Long khachsanId){
//	        return danhgiaService.getListDanhgia(Long.parseLong("3"));
//	    }

	
	@GetMapping("/list")
public String list() {
	return "NewFile";
}

}