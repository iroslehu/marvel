package com.albo.marvel.marvel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item{
    @JsonProperty("name") 
    public String getName() { 
		 return this.name;
    }
    public void setName(String name) { 
		 this.name = name;
    }
    String name;

    @JsonProperty("role") 
    public String getRole() { 
		 return this.role;
    }
    public void setRole(String role) { 
		 this.role = role;
    }
    String role;
}
