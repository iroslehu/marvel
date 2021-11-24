package com.albo.marvel.model.marvel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RootCharacters {
    @JsonProperty("data")
    public DataCharacters getData() {
		 return this.data;
    }
    public void setData(DataCharacters data) {
		 this.data = data;
    }
    DataCharacters data;
}
