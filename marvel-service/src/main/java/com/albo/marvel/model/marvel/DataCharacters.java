package com.albo.marvel.model.marvel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataCharacters {
    @JsonProperty("offset")
    public int getOffset() { 
		 return this.offset;
    }
    public void setOffset(int offset) { 
		 this.offset = offset;
    }
    int offset;

    @JsonProperty("limit") 
    public int getLimit() { 
		 return this.limit;
    }
    public void setLimit(int limit) { 
		 this.limit = limit;
    }
    int limit;

    @JsonProperty("total") 
    public int getTotal() { 
		 return this.total;
    }
    public void setTotal(int total) { 
		 this.total = total;
    }
    int total;

    @JsonProperty("count") 
    public int getCount() { 
		 return this.count;
    }
    public void setCount(int count) { 
		 this.count = count;
    }
    int count;

    @JsonProperty("results") 
    public List<ResultCharacters> getResults() {
		 return this.results;
    }
    public void setResults(List<ResultCharacters> results) {
		 this.results = results;
    }
    List<ResultCharacters> results;
}
