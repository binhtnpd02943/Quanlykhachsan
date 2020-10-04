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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.fpt.dto.cityDto;
import edu.poly.fpt.dto.hotelDto;
import edu.poly.fpt.dto.typeDto;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.ThanhPho;

import edu.poly.fpt.services.ThanhphoService;

@Controller
@RequestMapping("/citys")
public class ThanhphoController {

	@Autowired
	private ThanhphoService thanhphoService;

	private static final int BUTTONS_TO_SHOW = 3;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;

	@GetMapping("/")
	public ModelAndView homepage(@RequestParam("page") Optional<Integer> page) {
		return getStaffsAndPage(new cityDto(), page);
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated cityDto cityDto, BindingResult result,
			RedirectAttributes redire) {

		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng nhập các trường bắt buộc!!");
			model.addAttribute("cityDto", cityDto);
			return "citys/dsThanhpho";
		}
//add new
		if (cityDto.getId() != null && cityDto.getId() > 0) {
		}
		Path path = Paths.get("images/");
		try {
			InputStream inputStream = cityDto.getPhoto().getInputStream();
			Files.copy(inputStream, path.resolve(cityDto.getPhoto().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "error :" + e.getMessage());
		}

		ThanhPho city = new ThanhPho();
		city.setId(cityDto.getId());
		city.setTen(cityDto.getTen());
		city.setMota(cityDto.getMota());
		if (cityDto.getPhoto().isEmpty()) {
			ThanhPho oldStaff = thanhphoService.findById(cityDto.getId()).get();
			city.setUrlhinhanh(oldStaff.getUrlhinhanh());
		} else {
			city.setUrlhinhanh(cityDto.getPhoto().getOriginalFilename());
		}
		thanhphoService.save(city);

		model.addAttribute("cityDto", cityDto);
		System.out.println("==================================" + cityDto.getId());
		redire.addFlashAttribute("success", "Saved Citys successfully!");
		return "redirect:/citys/";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, @RequestParam("page") Optional<Integer> page) {
		ThanhPho city = thanhphoService.findById(id).get();
		cityDto cityDto = new cityDto();

		cityDto.setId(city.getId());
		cityDto.setTen(city.getTen());
		cityDto.setMota(city.getMota());
		cityDto.setImageName(city.getUrlhinhanh());
		return getStaffsAndPage(cityDto, page);
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redire) {
		thanhphoService.deleteById(id);
		redire.addFlashAttribute("success", "Deleted Citys successfully!");
		return "redirect:/citys/";
	}

	@ModelAttribute("citys")
	public List<ThanhPho> getDeparts() {
		return (List<ThanhPho>) thanhphoService.findAll();
	}

	@PostMapping("find")
	public String find(@RequestParam(defaultValue = "") String ten, ModelMap model, @RequestParam("page") Optional<Integer> page ) {

		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<ThanhPho> list = thanhphoService.findByTenLikeOrderByTen(ten,PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id"))); // day phai trả về 1 page ko phải list
		PagerModel pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("cityDto", new cityDto());
		model.addAttribute("citys", list);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);
		return "citys/dsThanhpho";
	}

	public ModelAndView getStaffsAndPage(cityDto cityDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("citys/dsThanhpho");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<ThanhPho> staffList = thanhphoService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(staffList.getTotalPages(), staffList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("cityDto", cityDto);
		modelAndView.addObject("citys", staffList);
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
