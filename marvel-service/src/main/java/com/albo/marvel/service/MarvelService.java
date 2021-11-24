package com.albo.marvel.service;

public interface MarvelService {
    boolean getMarvelColaborators(String name);
    boolean getMarvelCharacters(String name);
    boolean sync();
}
