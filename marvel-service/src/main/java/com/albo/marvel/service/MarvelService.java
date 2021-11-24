package com.albo.marvel.service;

import com.albo.marvel.model.response.CharactersResponse;

public interface MarvelService {
    boolean getMarvelColaborators(String name);
    CharactersResponse getMarvelCharacters(String name);
    boolean sync();
}
