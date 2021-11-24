package com.albo.marvel.controller;

import com.albo.marvel.exceptionhandling.CustomException;
import com.albo.marvel.service.MarvelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarvelController {

	private MarvelService marvelService;

	@Autowired
	public MarvelController(MarvelService marvelService){
		this.marvelService = marvelService;
	}

	@RequestMapping("/")
	public String home(){
		return "Application runing!";
	}

	@GetMapping("/marvel/sync")
	public String sync() throws CustomException {
		return marvelService.sync() ? "Data Synced" : "Data not Synced";
	}

	@GetMapping("/marvel/colaborators/{name}")
	public boolean getAllColaborators(@PathVariable String name) throws CustomException {
		return marvelService.getMarvelColaborators(name);
	}

	@GetMapping("/marvel/characters/{name}")
	@ExceptionHandler({ CustomException.class})
	public boolean getAllCharacters(@PathVariable String name)  throws CustomException {
		return marvelService.getMarvelCharacters(name);
	}

}
