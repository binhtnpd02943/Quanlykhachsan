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

import edu.poly.fpt.dto.typeDto;

import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.PagerModel;

import edu.poly.fpt.services.LoaikhachsanService;

@Controller
@RequestMapping("/types")
public class LoaikhachsanController {

	@Autowired
	private LoaikhachsanService loaikhachsanService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;

	@GetMapping("/")
	public ModelAndView homepage(@RequestParam("page") Optional<Integer> page) {
		return getTypesAndPage(new typeDto(), page);
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated typeDto typeDto, BindingResult result,
			RedirectAttributes redire) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng nhập các trường bắt buộc!!");
			model.addAttribute("typeDto", typeDto);
			return "types/dsTypes";
		}

//add new
		if (typeDto.getId() != null && typeDto.getId() > 0) {
		}
		Path path = Paths.get("images/");

		try {
			InputStream inputStream = typeDto.getPhoto().getInputStream();
			Files.copy(inputStream, path.resolve(typeDto.getPhoto().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "error :" + e.getMessage());
		}

		LoaiKhachSan city = new LoaiKhachSan();
		city.setId(typeDto.getId());
		city.setTen(typeDto.getTen());
		city.setMota(typeDto.getMota());
		if (typeDto.getPhoto().isEmpty()) {
			LoaiKhachSan oldStaff = loaikhachsanService.findById(typeDto.getId()).get();
			city.setUrlhinhanh(oldStaff.getUrlhinhanh());
		} else {
			city.setUrlhinhanh(typeDto.getPhoto().getOriginalFilename());
		}

		loaikhachsanService.save(city);
		redire.addFlashAttribute("success", "Saved Citys successfully!");
		model.addAttribute("typeDto", typeDto);
		System.out.println("==================================" + typeDto.getId());

		return "redirect:/types/";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, @RequestParam("page") Optional<Integer> page) {
		LoaiKhachSan type = loaikhachsanService.findById(id).get();
		typeDto typeDto = new typeDto();

		typeDto.setId(type.getId());
		typeDto.setTen(type.getTen());
		typeDto.setMota(type.getMota());
		typeDto.setImageName(type.getUrlhinhanh());

		return getTypesAndPage(typeDto, page);
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redire) {
		loaikhachsanService.deleteById(id);
		redire.addFlashAttribute("success", "Delete Citys successfully!");
		return "redirect:/types/";
	}

	@ModelAttribute("types")
	public List<LoaiKhachSan> getTypeHotels() {
		return loaikhachsanService.findAll();
	}

	@PostMapping("find")
	 public String find(ModelMap model, @RequestParam(defaultValue = "")String ten, @RequestParam("page") Optional<Integer> page ){
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<LoaiKhachSan> list = loaikhachsanService.findByTenLikeOrderByTen(ten,PageRequest.of(evalPage, INITIAL_PAGE_SIZE,Sort.by("id")));
		PagerModel pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("typeDto", new typeDto());
		model.addAttribute("types", list);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);
		return "types/dsTypes";
	}

	public ModelAndView getTypesAndPage(typeDto typeDto, @RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = new ModelAndView("types/dsTypes");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<LoaiKhachSan> staffList = loaikhachsanService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(staffList.getTotalPages(), staffList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("typeDto", typeDto);
		modelAndView.addObject("types", staffList);
		modelAndView.addObject("selectedPageSize", INITIAL_PAGE_SIZE);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@ModelAttribute("attr_user")
	public org.springframework.security.core.userdetails.User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (User) auth.getPrincipal();
	}
}
