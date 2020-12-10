package edu.poly.fpt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/404")
public class NotfoundController {
	@RequestMapping("/")
	public String new404() {
		return "404NotFound/404";
	}
}
