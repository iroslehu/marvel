package com.albo.marvel.model.marvel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreatorsComics {
    @JsonProperty("items")
    public List<Item> getItems() {
		 return this.items;
    }
    public void setItems(List<Item> items) { 
		 this.items = items;
    }
    List<Item> items;
}
