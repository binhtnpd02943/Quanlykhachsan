package edu.poly.fpt.controllers;

import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.services.PhongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/thongke")
public class thongkecontroller {
	
	@Autowired
    private PhongService phongService;

    @GetMapping("/ThongKeTheoNgay")
    public String thongKeTheoNgay(ModelMap model) {
    	Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date);
        c2.setTime(date);
        
        c2.roll(Calendar.MONTH, -1);
        List<String> listThongKeNgay = new ArrayList<>();
        while (c1.after(c2) == true) {
        	String result = format.format(c1.getTime());
        	listThongKeNgay.add(result);
        	c1.add(Calendar.DATE, -1);
        }
        model.addAttribute("listngay", listThongKeNgay);
        String currentday= format.format(date);
        List<Object> thongke = phongService.thongKeTheoNgay(currentday);
        model.addAttribute("thongke",thongke);
        return "rooms/ThongKeTheoNgay";
    }
    
    @GetMapping("/ThongKeTheoNgay/filter")
    public String thongKeTheoNgayFilter(ModelMap model, @RequestParam("ngay") String ngay) {
    	Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date);
        c2.setTime(date);
        
        c2.roll(Calendar.MONTH, -1);
        List<String> listThongKeNgay = new ArrayList<>();
        while (c2.before(c1) == true) {
        	String result = format.format(c2.getTime());
        	listThongKeNgay.add(result);
        	c2.add(Calendar.DATE, 1);
        }
        model.addAttribute("listngay", listThongKeNgay);
        List<Object> thongke = phongService.thongKeTheoNgay(ngay);
        model.addAttribute("thongke",thongke);
        return "rooms/ThongKeTheoNgay";
    }
    
    @GetMapping("/ThongKeTheoThang")
    public String thongKeTheoThang(ModelMap model) {
    	Date date = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int year = cal.get(Calendar.YEAR);
    	int month = cal.get(Calendar.MONTH);
        List<Object> thongke = phongService.thongKeTheoThang(month, year);
        model.addAttribute("thongke",thongke);
        return "rooms/ThongKeTheoThang";
    }
    
    @GetMapping("/ThongKeTheoThang/filter")
    public String thongKeTheoThangFilter(ModelMap model, @RequestParam("thang") int thang,@RequestParam("nam") int nam) {
        List<Object> thongke = phongService.thongKeTheoThang(thang, nam);
        model.addAttribute("thongke",thongke);
        System.out.println(thongke);
        return "rooms/ThongKeTheoThang";
    }
}
