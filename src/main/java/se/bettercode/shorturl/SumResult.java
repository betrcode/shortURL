package se.bettercode.shorturl;

import org.springframework.data.annotation.Id;

public class SumResult {

    @Id
    private String id;

    private Integer total;

    public SumResult(String id, Integer total) {
        this.id = id;
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

}
