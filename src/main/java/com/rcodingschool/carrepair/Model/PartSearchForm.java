package com.rcodingschool.carrepair.Model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;

public class PartSearchForm {

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "The partID must be greater or equal than 1!")
    private Long partID;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "The start price must be greater or equal than 1!")
    private Long partPriceStart;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 1, message = "The end price must be greater or equal than 1!")
    private Long partPriceEnd;

    public Long getPartID() {
        return partID;
    }

    public void setPartID(Long partID) {
        this.partID = partID;
    }

    public Long getPartPriceStart() {
        return partPriceStart;
    }

    public void setPartPriceStart(Long partPriceStart) {
        this.partPriceStart = partPriceStart;
    }

    public Long getPartPriceEnd() {
        return partPriceEnd;
    }

    public void setPartPriceEnd(Long partPriceEnd) {
        this.partPriceEnd = partPriceEnd;
    }
}
