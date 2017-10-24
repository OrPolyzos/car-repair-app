package com.rcodingschool.carrepair.Model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PartSearchForm {

    @Size(min=1, max=9, message="PartID should be until 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="PartID should contain only digits!")
    private String partID;

    @Size(min=1, max=9, message="The partPrice1 should be until 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="The partPriceStart must contain only digits!")
    private String partPriceStart;

    @Size(min=1, max=9, message="The partPrice2 should be until 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="The partPriceEnd must contain only digits!")
    private String partPriceEnd;

    public String getPartID() { return partID; }

    public void setPartID(String partID) { this.partID = partID; }

    public String getPartPriceStart() { return partPriceStart; }

    public void setPartPriceStart(String partPriceStart) { this.partPriceStart = partPriceStart; }

    public String getPartPriceEnd() { return partPriceEnd; }

    public void setPartPriceEnd(String partPriceEnd) { this.partPriceEnd = partPriceEnd; }


}
