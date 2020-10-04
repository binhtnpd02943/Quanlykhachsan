package edu.poly.fpt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.fpt.services.DanhgiaService;

@Controller
@RequestMapping("/danhgias")
public class DanhgiaController {
	@Autowired
	private DanhgiaService danhgiaService;

}
