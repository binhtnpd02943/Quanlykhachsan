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

import edu.poly.fpt.dto.hotelDto;
import edu.poly.fpt.dto.roomDto;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.ThanhPho;

import edu.poly.fpt.services.PhongService;

@Controller
@RequestMapping("/rooms")
public class PhongController {
	@Autowired
	private PhongService phongService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;

	@GetMapping("/")
	public ModelAndView homepage(@RequestParam("page") Optional<Integer> page) {
		return getStaffsAndPage(new roomDto(), page);
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated roomDto roomDto, BindingResult result,
			RedirectAttributes redire) {
		if (result.hasErrors()) {
			model.addAttribute("message", "vui long nhap tat ca cac du lieu!!");
			model.addAttribute("roomDto", roomDto);
			return "rooms/dsphong";
		}

		// add new
		if (roomDto.getId() != null && roomDto.getId() > 0) {
		}
		Path path = Paths.get("images/");

		try {
			InputStream inputStream = roomDto.getPhoto().getInputStream();
			Files.copy(inputStream, path.resolve(roomDto.getPhoto().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "error :" + e.getMessage());
		}

		Phong room = new Phong();

		room.setId(roomDto.getId());
		room.setTen(roomDto.getTen());
		room.setDientich(roomDto.getDientich());
		room.setGiathue(roomDto.getGiathue());
		room.setTiennghi(roomDto.getTiennghi());
		room.setLoaigiuong(roomDto.getLoaigiuong());
		if (roomDto.getPhoto().isEmpty()) {
			Phong oldStaff = phongService.findById(roomDto.getId()).get();
			room.setUrlhinhanh(oldStaff.getUrlhinhanh());
		} else {
			room.setUrlhinhanh(roomDto.getPhoto().getOriginalFilename());
		}

		room.setMota(roomDto.getMota());

		KhachSan ks = new KhachSan();
		ks.setId(roomDto.getKhachsanId());
		room.setKhachsan(ks);

		phongService.save(room);

		model.addAttribute("roomDto", roomDto);
		System.out.println("==================================" + roomDto.getId());
		redire.addFlashAttribute("success", "Saved Rooms successfully!");
		return "redirect:/rooms/";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, @RequestParam("page") Optional<Integer> page) {
		Phong room = phongService.findById(id).get();
		roomDto roomDto = new roomDto();

		roomDto.setId(room.getId());
		roomDto.setTen(room.getTen());
		roomDto.setDientich(room.getDientich());
		roomDto.setGiathue(room.getGiathue());
		roomDto.setTiennghi(roomDto.getTiennghi());
		roomDto.setLoaigiuong(room.getLoaigiuong());
		roomDto.setImageName(room.getUrlhinhanh());
		roomDto.setMota(room.getMota());
		roomDto.setKhachsanId(room.getKhachsan().getId());

		return getStaffsAndPage(roomDto, page);
	}
	 
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redire) {
		phongService.deleteById(id);
		redire.addFlashAttribute("success", "Deleted Rooms successfully!");
		return "redirect:/rooms/";
	}

	@PostMapping("find")
	public String find(ModelMap model, @RequestParam(defaultValue = "") String ten,
			@RequestParam("page") Optional<Integer> page) {
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<Phong> list = phongService.findByTenLikeOrderByTen(ten,
				PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		model.addAttribute("roomDto", new roomDto());
		model.addAttribute("rooms", list);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZE);
		model.addAttribute("pager", pager);
		return "rooms/dsPhong";
	}

	@ModelAttribute("rooms")
	public List<Phong> getPhongs() {
		return (List<Phong>) phongService.findAll();
	}

	@ModelAttribute(name = "hotels")
	public List<KhachSan> getThanhpho() {
		return phongService.findAllKhachsan();
	}

	public ModelAndView getStaffsAndPage(roomDto roomDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("rooms/dsphong");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<Phong> roomList = phongService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("roomDto", roomDto);
		modelAndView.addObject("rooms", roomList);
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
