package com.dcm.work.presto.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TestModel {
    private String id;
    private String ccode;
    private String cname;
    private String pcode;
    private String pname;
    private String month;
    private BigDecimal sleepDays;
    private BigDecimal offLineDays;
    private BigDecimal intervalDays;
    private BigDecimal onlineDays;
    private String dt;
}

