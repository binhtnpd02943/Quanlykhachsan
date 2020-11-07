package edu.poly.fpt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.poly.fpt.dto.cityDto;
import edu.poly.fpt.dto.hotelDto;
import edu.poly.fpt.dto.roomDto;
import edu.poly.fpt.dto.serviceDto;
import edu.poly.fpt.dto.typeDto;
import edu.poly.fpt.dto.userDto;
import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.PagerModel;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.entities.ThanhPho;
import edu.poly.fpt.services.DichvuService;
import edu.poly.fpt.services.KhachsanService;
import edu.poly.fpt.services.LoaikhachsanService;
import edu.poly.fpt.services.PhongService;
import edu.poly.fpt.services.TaikhoanService;
import edu.poly.fpt.services.ThanhphoService;

@Controller
@RequestMapping("/view")
public class ListAllCustomer {
	
	@Autowired
	private DichvuService dichvuService;

	@Autowired
	private PhongService phongService;
	@Autowired
	private KhachsanService khachsanService;

	@Autowired
	private TaikhoanService taikhoanService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	private static final int BUTTONS_TO_SHOW = 7;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 3; // list 3 sản phẩm khách sạn trang phụ
	private static final int INITIAL_PAGE_SIZES = 9; //List 9 sản phẩm khách sạn chính 
	private static final int INITIAL_PAGE_SIZESS = 5; // view/saveUser list lại page 5 phòng
	private static final int INITIAL_PAGE_SIZEs = 9;// filter tìm kiếm khách sạn
	
	private static final int INITIAL_PAGE_SIZEz = 15; // list services

	@GetMapping("/room-packages-grid")
	public ModelAndView Homepage(@RequestParam("page") Optional<Integer> page) {
		return getHotelAndPage(new hotelDto(), page);
	}

	public ModelAndView getHotelAndPage(hotelDto hotelDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("customer/room-packages-grid");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<KhachSan> staffList = khachsanService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZES, Sort.by("id")));
		PagerModel pager = new PagerModel(staffList.getTotalPages(), staffList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("hotelDto", hotelDto);
		modelAndView.addObject("hotel", staffList);
		modelAndView.addObject("selectedPageSize", INITIAL_PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@GetMapping("/rooms-packages")
	public ModelAndView Home(@RequestParam("page") Optional<Integer> page) {
		return getCityAndPage(new hotelDto(), page);
	}

	public ModelAndView getCityAndPage(hotelDto hotelDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("customer/rooms-packages");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<KhachSan> staffList = khachsanService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZE, Sort.by("id")));
		PagerModel pager = new PagerModel(staffList.getTotalPages(), staffList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("hotelDto", hotelDto);
		modelAndView.addObject("hotel", staffList);
		modelAndView.addObject("selectedPageSize", INITIAL_PAGE_SIZE);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@GetMapping("profile/{id}")
	public String profile(@PathVariable("id") Long id, ModelMap model) {
		if (khachsanService.findById(id).isPresent()) {
			model.addAttribute("item", khachsanService.findById(id).get());
			return "customer/packages-detail";
		}
		return "redirect:view/";
	}
	@GetMapping("profiles/{id}")
	public String profiles(@PathVariable("id") Integer id, ModelMap model) {
		if (dichvuService.findById(id).isPresent()) {
			model.addAttribute("item", dichvuService.findById(id).get());
			return "customer/service-detail";
		}
		return "redirect:view/";
		
	}

	@PostMapping("filter")
	public String filter(@RequestParam(defaultValue = "") String ten, @RequestParam("lks") int lks,
			@RequestParam("dg") int dg, @RequestParam("giatn") int min, @RequestParam("giacn") int max, ModelMap model,
			@RequestParam("page") Optional<Integer> page) {
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<KhachSan> list = null;
		PagerModel pager = null;
		System.out.println(ten);
		System.out.println(lks);
		System.out.println(dg);
		if (!ten.isEmpty() && lks == 0) {
			list = khachsanService.filterKhachSanbyCity(ten, dg, min, max,
					PageRequest.of(evalPage, INITIAL_PAGE_SIZEs, Sort.by("id")));
			pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		} else if (ten.isEmpty() && lks != 0) {
			list = khachsanService.filterKhachSanbyTypeofCity(lks, dg, min, max,
					PageRequest.of(evalPage, INITIAL_PAGE_SIZEs, Sort.by("id")));
			pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		} else if (!ten.isEmpty() && lks != 0) {
			list = khachsanService.filterKhachSanbyAll(ten, lks, dg, min, max,
					PageRequest.of(evalPage, INITIAL_PAGE_SIZEs, Sort.by("id")));
			pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		} else {
			list = khachsanService.filterKhachSanbyNone(dg, min, max,
					PageRequest.of(evalPage, INITIAL_PAGE_SIZEs, Sort.by("id")));
			pager = new PagerModel(list.getTotalPages(), list.getNumber(), BUTTONS_TO_SHOW);
		}
		model.addAttribute("hotelDto", new hotelDto());
		model.addAttribute("hotel", list);
		model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZEs);
		model.addAttribute("pager", pager);
		return "customer/room-packages-grid";
	}

	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String save(ModelMap model, @Validated userDto UserDto, BindingResult result, RedirectAttributes redire,
			@RequestParam("page") Optional<Integer> page) {
		if (taikhoanService.existsById(UserDto.getTentaikhoan())) {
			model.addAttribute("edit", true);
		}
		if (result.hasErrors()) {
			model.addAttribute("userDto", UserDto);
			int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
			Page<Phong> roomList = phongService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZESS, Sort.by("id")));
			PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
			model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZESS);
			model.addAttribute("roomDto", new roomDto());
			model.addAttribute("phong", roomList);
			model.addAttribute("pager", pager);
			return "customer/index";
		}
		if (!UserDto.getMatkhau().equals(UserDto.getReMatkhau())) {
			model.addAttribute("userDto", UserDto);
			
			int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
			Page<Phong> roomList = phongService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZESS, Sort.by("id")));
			PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
			model.addAttribute("selectedPageSize", INITIAL_PAGE_SIZESS);
			model.addAttribute("roomDto", new roomDto());
			model.addAttribute("phong", roomList);
			model.addAttribute("pager", pager);
			model.addAttribute("err", true);
			return "customer/index";
		}

		TaiKhoan user = new TaiKhoan();
		user.setTentaikhoan(UserDto.getTentaikhoan());
		user.setMatkhau(passwordEncoder.encode(UserDto.getMatkhau()));
		user.setHoten(UserDto.getHoten());
		user.setGioitinh(UserDto.getGioitinh());

		user.setSodt(UserDto.getSodt());
		user.setEmail(UserDto.getEmail());
		user.setRole("USER");
		taikhoanService.save(user);
		model.addAttribute("userDto", UserDto);
		System.out.println("==================================" + UserDto.getTentaikhoan());
		redire.addFlashAttribute("success", "Saved Users successfully!");
		return "redirect:/";
	}

	@ModelAttribute(name = "loaikhachsan")
	public List<LoaiKhachSan> getLoaikhachsan() {
		return khachsanService.findAllLoaikhachsan();
	}
	
	
	
	
	
	
	
	

	@GetMapping("/services")
	public ModelAndView homepage(@RequestParam("page") Optional<Integer> page) {
		return getServiceAndPage(new serviceDto(), page);
	}
	
	public ModelAndView getServiceAndPage(serviceDto serviceDto, @RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("customer/services");
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		Page<DichVu> roomList = dichvuService.findAll(PageRequest.of(evalPage, INITIAL_PAGE_SIZEz, Sort.by("id")));
		PagerModel pager = new PagerModel(roomList.getTotalPages(), roomList.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("serviceDto", serviceDto);
		modelAndView.addObject("services", roomList);
		modelAndView.addObject("selectedPageSize", INITIAL_PAGE_SIZEz);
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
