package com.albo.marvel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarvelController {

	@RequestMapping("/")
	public String home(){
		return "Application runing!";
	}
}
