package com.albo.marvel.model.marvel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultComics {

    @JsonProperty("title")
    public String getTitle() {
        return this.title; }
    public void setTitle(String title) {
        this.title = title; }
    String title;

    @JsonProperty("creators")
    public CreatorsComics getCreatorsComics() {
        return this.creatorsComics;
    }
    public void setCreatorsComics(CreatorsComics creatorsComics) {
        this.creatorsComics = creatorsComics;
    }
    CreatorsComics creatorsComics;

    @JsonProperty("characters")
    public ColaboratorsComics getColaboratorsComics() {
        return this.colaboratorsComics ;
    }
    public void setColaboratorsComics (ColaboratorsComics colaboratorsComics ) {
        this.colaboratorsComics = colaboratorsComics ;
    }
    ColaboratorsComics colaboratorsComics ;

}
