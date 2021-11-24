package com.albo.marvel.model.marvel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RootComics {
    @JsonProperty("data")
    public DataComics getData() {
		 return this.data;
    }
    public void setData(DataComics data) {
		 this.data = data;
    }
    DataComics data;
}
