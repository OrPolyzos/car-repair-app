package com.rcodingschool.carrepair.Model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PartSearchForm {

    @Size(min=1, max=9, message="PartID should be until 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="PartID should contain only digits!")
    private String partID;

    @Size(min=1, max=9, message="The partPrice1 should be until 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="The partPrice1 must contain only digits!")
    private String partPrice1;

    @Size(min=1, max=9, message="The partPrice2 should be until 9 digits!")
    @Pattern(regexp="^[0-9]{9}", message="The partPrice2 must contain only digits!")
    private String partPrice2;

    public String getPartID() { return partID; }

    public void setPartID(String partID) { this.partID = partID; }

    public String getPartPrice1() { return partPrice1; }

    public void setPartPrice1(String partPrice1) { this.partPrice1 = partPrice1; }

    public String getPartPrice2() { return partPrice2; }

    public void setPartPrice2(String partPrice2) { this.partPrice2 = partPrice2; }


}
