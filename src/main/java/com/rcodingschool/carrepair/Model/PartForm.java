package com.rcodingschool.carrepair.Model;


import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PartForm {

    private Long partID;

    @NotNull(message="This field is required!")
    @Size(min=1, max=128, message="Maximum length is 128 characters!")
    @Pattern(regexp="^[a-zA-Z]{1,128}", message="Only uppercase and lowercase characters allowed!")
    private String partName;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=0, message="PartPrice must be to 0!")
    private Integer partPrice;



    public Long getPartID() { return partID; }

    public void setPartID(Long partID) { this.partID = partID; }

    public String getPartName() {return partName;}

    public void setPartName(String partName) { this.partName = partName;}

    public Integer getPartPrice() {return partPrice;}

    public void setPartPrice(Integer partPrice){this.partPrice = partPrice;}


}
