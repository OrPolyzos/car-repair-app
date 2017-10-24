package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;

public class PartSearchForm {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long partID;


    private String partPrice;

    public Long getPartID() { return partID; }

    public void setPartID(Long partID) { this.partID = partID; }


}
