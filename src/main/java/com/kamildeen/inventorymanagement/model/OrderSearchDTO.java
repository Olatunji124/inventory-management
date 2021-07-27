package com.kamildeen.inventorymanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderSearchDTO {

    private String phoneNumber;
//    @JsonFormat(pattern = )
    private String startDate;
    private String endDate;
}
