package edu.poly.fpt.controllers;

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

import edu.poly.fpt.dto.roomDto;
import edu.poly.fpt.dto.serviceDto;
import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.services.DichvuService;

@Controller
@RequestMapping("/services")
public class DichvuController {
	@Autowired
	private DichvuService dichvuService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;

	@GetMapping("/")
	public ModelAndView homepage(@RequestParam("page") Optional<Integer> page) {
		return getStaffsAndPage(new serviceDto(), page);
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated serviceDto serviceDto, BindingResult result,
			RedirectAttributes redire) {
		if (result.hasErrors()) {
			model.addAttribute("message", "vui long nhap tat ca cac du lieu!!");
			model.addAttribute("roomDto", serviceDto);
			return "services/dsDichvu";
		}

		// add new
		if (serviceDto.getId() != null && serviceDto.getId() > 0) {
		}

		DichVu dv = new DichVu();

		dv.setId(serviceDto.getId());
		dv.setTen(serviceDto.getTen());
		dv.setGiadv(serviceDto.getGiadv());

		KhachSan ks = new KhachSan();
		ks.setId(serviceDto.getKhachsanId());
		dv.setKhachsan(ks);

		dichvuService.save(dv);

		model.addAttribute("roomDto", serviceDto);
		System.out.println("==================================" + serviceDto.getId());
		redire.addFlashAttribute("success", "Saved Services successfully!");
		return "redirect:/services/";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, @RequestParam("page") Optional<Integer> page) {
		DichVu dv = dichvuService.findById(id).get();
		serviceDto serviceDto = new serviceDto();

		serviceDto.setId(dv.getId());
		serviceDto.setTen(dv.getTen());
		serviceDto.setGiadv(dv.getGiadv());
		serviceDto.setKhachsanId(dv.getKhachsan().getId());

		return getStaffsAndPage(serviceDto, page);
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redire) {
		dichvuService.deleteById(id);
		redire.addFlashAttribute("success", "Deleted Rooms successfully!");
		return "redirect:/services/";
	}

	@PostMapping("find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String ten,
			@RequestParam("page") Optional<Integer> page) {
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<DichVu> list = dichvuService.findByTenLikeOrderByTen(ten,
				PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("serviceDto", new serviceDto());
		model.addAttribute("services", list);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);
		return "services/dsDichvu";
	}

	@ModelAttribute(name = "hotels")
	public List<KhachSan> getHotels() {
		return dichvuService.findAllKhachsan();
	}

	@ModelAttribute(name = "services")
	public List<DichVu> getDichvu() {
		return (List<DichVu>) dichvuService.findAll();
	}

	public ModelAndView getStaffsAndPage(serviceDto serviceDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("services/dsDichvu");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<DichVu> roomList = dichvuService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("serviceDto", serviceDto);
		modelAndView.addObject("services", roomList);
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
