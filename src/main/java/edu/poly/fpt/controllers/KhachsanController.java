package edu.poly.fpt.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.fpt.dto.hotelDto;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.ThanhPho;

import edu.poly.fpt.services.KhachsanService;

@Controller
@RequestMapping("/hotels")
public class KhachsanController {
	@Autowired
	private KhachsanService khachsanService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;

	@GetMapping("/")
	public ModelAndView homepage(@RequestParam("page") Optional<Integer> page) {
		return getStaffsAndPage(new hotelDto(), page);
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated hotelDto khachsanDto, BindingResult result,
			RedirectAttributes redire) {
//		String filename =null;

		if (result.hasErrors()) {
			model.addAttribute("message", "vui long nhap tat ca cac du lieu!!");
			model.addAttribute("hotelDto", khachsanDto);
			return "Hotel/dsKhachsan";
		}

//add new
		if (khachsanDto.getId() != null && khachsanDto.getId() > 0) {
		}
		Path path = Paths.get("images/");

		try {
			InputStream inputStream = khachsanDto.getPhoto().getInputStream();
			Files.copy(inputStream, path.resolve(khachsanDto.getPhoto().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
//			filename = khachsanDto.getPhoto().getOriginalFilename().toString();

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "error :" + e.getMessage());
		}

		KhachSan ks = new KhachSan();
		ks.setId(khachsanDto.getId());
		ks.setTen(khachsanDto.getTen());
		ks.setDiachi(khachsanDto.getDiaChi());
		ks.setSodt(khachsanDto.getSodt());
		ks.setCachtrungtam(khachsanDto.getCachtrungtam());

		if (khachsanDto.getPhoto().isEmpty()) {
			KhachSan oldStaff = khachsanService.findById(khachsanDto.getId()).get();
			ks.setUrlhinhanh(oldStaff.getUrlhinhanh());
		} else {
			ks.setUrlhinhanh(khachsanDto.getPhoto().getOriginalFilename());
		}

		ks.setMota(khachsanDto.getMota());
		ks.setCachbien(khachsanDto.getCachbien());
		ks.setDanhgia(khachsanDto.getDanhgia());
		ks.setBuaan(khachsanDto.getBuaan());

		ThanhPho tp = new ThanhPho();
		tp.setId(khachsanDto.getThanhphoId());
		ks.setThanhpho(tp);

		LoaiKhachSan lks = new LoaiKhachSan();
		lks.setId(khachsanDto.getLoaiKhachSanId());
		ks.setLoaikhachsan(lks);

		khachsanService.save(ks);

		model.addAttribute("hotelDto", khachsanDto);
		System.out.println("==================================" + khachsanDto.getId());
		redire.addFlashAttribute("success", "Saved Hotels successfully!");
		return "redirect:/hotels/";
	}

	@ModelAttribute(name = "tbthanhpho")
	public List<ThanhPho> getThanhpho() {
		return khachsanService.findAllThanhpho();
	}

	@ModelAttribute(name = "tbloaikhachsan")
	public List<LoaiKhachSan> getLoaikhachsan() {
		return khachsanService.findAllLoaikhachsan();
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id, @RequestParam("page") Optional<Integer> page) {
		KhachSan ks = khachsanService.findById(id).get();
		hotelDto hotelDto = new hotelDto();

		hotelDto.setId(ks.getId());
		hotelDto.setTen(ks.getTen());
		hotelDto.setDiaChi(ks.getDiachi());
		hotelDto.setSodt(ks.getSodt());
		hotelDto.setCachtrungtam(ks.getCachtrungtam());
		hotelDto.setImageName(ks.getUrlhinhanh());
		hotelDto.setMota(ks.getMota());
		hotelDto.setCachbien(ks.getCachbien());
		hotelDto.setDanhgia(ks.getDanhgia());
		hotelDto.setBuaan(ks.getBuaan());
		hotelDto.setThanhphoId(ks.getThanhpho().getId());
		hotelDto.setLoaiKhachSanId(ks.getLoaikhachsan().getId());

		return getStaffsAndPage(hotelDto, page);
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redire) {
		khachsanService.deleteById(id);
		redire.addFlashAttribute("success", "Deleted Hotels successfully!");
		return "redirect:/hotels/";
	}

	@PostMapping("find")
	public String find(@RequestParam(defaultValue = "") String ten, ModelMap model,
			@RequestParam("page") Optional<Integer> page) {
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		if(ten==null) {
			
		}
		Page<KhachSan> list = khachsanService.findByTenLikeOrderByTen(ten,
				PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("hotelDto", new hotelDto());
		model.addAttribute("hotels", list);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);
		return "Hotel/dsKhachsan";
	}
	
	


	public ModelAndView getStaffsAndPage(hotelDto hotelDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("Hotel/dsKhachsan");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<KhachSan> staffList = khachsanService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(staffList.getTotalPages(), staffList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("hotelDto", hotelDto);
		modelAndView.addObject("hotels", staffList);
		modelAndView.addObject("selectedPageSize", INITIAL_PAGE_SIZE);
		modelAndView.addObject("pager", pager);
		return modelAndView;
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
