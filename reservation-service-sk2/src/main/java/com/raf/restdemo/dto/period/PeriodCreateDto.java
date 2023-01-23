package com.raf.restdemo.dto.period;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PeriodCreateDto {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
