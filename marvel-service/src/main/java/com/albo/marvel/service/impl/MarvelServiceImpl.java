package com.albo.marvel.service.impl;

import com.albo.marvel.service.MarvelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarvelServiceImpl implements MarvelService {

	private static final Logger logger = LoggerFactory.getLogger(MarvelServiceImpl.class);


	@Autowired
	public MarvelServiceImpl() {

	}

	@Override
	public boolean getMarvelColaborators(String name){

		return true;
	}

	@Override
	public boolean getMarvelCharacters(String name){


		return true;
	}

	@Async
	public boolean sync(){

		return true;
	}

}
