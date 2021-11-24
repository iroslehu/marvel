package com.albo.marvel.marvel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ColaboratorsComics {
    @JsonProperty("items")
    public List<Item> getItems() {
		 return this.items;
    }
    public void setItems(List<Item> items) { 
		 this.items = items;
    }
    List<Item> items;
}
