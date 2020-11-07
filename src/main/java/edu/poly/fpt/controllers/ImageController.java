package edu.poly.fpt.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.ThanhPho;
import edu.poly.fpt.services.DichvuService;
import edu.poly.fpt.services.KhachsanService;
import edu.poly.fpt.services.LoaikhachsanService;
import edu.poly.fpt.services.PhongService;
import edu.poly.fpt.services.ThanhphoService;



@Controller
public class ImageController {
	@Autowired
	private KhachsanService khachsanService;
	@Autowired
	private ThanhphoService thanhphoService;
	@Autowired
	private LoaikhachsanService loaikhachsanService; 
	@Autowired
	private PhongService phongService;
	@Autowired
	private DichvuService dichvuService;
	
	@RequestMapping( value = "getanh/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkAnh(@PathVariable Integer id){
		Optional<DichVu> sop = dichvuService.findById(id);
		
		if(sop.isPresent()) {
			DichVu dichvu = sop.get();
			try {
				Path filename = Paths.get("images", dichvu.getHinhanh());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("images/jpg"))
						.body(bsr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	@RequestMapping( value = "getimage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkImage(@PathVariable Long id){
		Optional<KhachSan> sop = khachsanService.findById(id);
		
		if(sop.isPresent()) {
			KhachSan khachsan = sop.get();
			try {
				Path filename = Paths.get("images", khachsan.getUrlhinhanh());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("images/jpg"))
						.body(bsr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping( value = "getimages/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkImage(@PathVariable Integer id){
		Optional<ThanhPho> sop = thanhphoService.findById(id);
		
		if(sop.isPresent()) {
			ThanhPho thanhpho = sop.get();
			try {
				Path filename = Paths.get("images", thanhpho.getUrlhinhanh());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("images/jpg"))
						.body(bsr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping( value = "getImage/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkImages(@PathVariable Integer id){
		Optional<LoaiKhachSan> sop = loaikhachsanService.findById(id);
		
		if(sop.isPresent()) {
			LoaiKhachSan thanhpho = sop.get();
			try {
				Path filename = Paths.get("images", thanhpho.getUrlhinhanh());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("images/jpg"))
						.body(bsr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@RequestMapping( value = "getImg/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> downloadLinkImg(@PathVariable Integer id){
		Optional<Phong> sop = phongService.findById(id);
		
		if(sop.isPresent()) {
			Phong phong = sop.get();
			try {
				Path filename = Paths.get("images", phong.getUrlhinhanh());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("images/jpg"))
						.body(bsr);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
