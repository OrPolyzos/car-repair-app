package com.rcodingschool.carrepair.Model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PartSearchForm {

    @Pattern(regexp="^[0-9]{9}", message="PartID should contain only digits!")
    private String partID;

    @Pattern(regexp="^[0-9]{9}", message="The partPriceStart must contain only digits!")
    private String partPriceStart;


    @Pattern(regexp="^[0-9]{9}", message="The partPriceEnd must contain only digits!")
    private String partPriceEnd;

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartPriceStart() {
        return partPriceStart;
    }

    public void setPartPriceStart(String partPriceStart) {
        this.partPriceStart = partPriceStart;
    }

    public String getPartPriceEnd() {
        return partPriceEnd;
    }

    public void setPartPriceEnd(String partPriceEnd) {
        this.partPriceEnd = partPriceEnd;
    }
}
