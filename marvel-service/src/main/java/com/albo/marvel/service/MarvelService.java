package com.albo.marvel.service;


import com.albo.marvel.model.response.CharactersResponse;
import com.albo.marvel.model.response.ColabratorsResponse;

public interface MarvelService {
    ColabratorsResponse getMarvelColaborators(String name);
    CharactersResponse getMarvelCharacters(String name);
    String sync();
    String delete();
}
