package com.raf.restdemo.dto.review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewCreateDto {

    @NotEmpty
    private String comment;

    @NotNull
    @Min(1) @Max(5)
    private int rate;

    @NotNull
    private Long firmId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }
}
