package com.rcodingschool.carrepair.Model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PartForm {

    private Long partID;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 128, message = "Maximum length is 128 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,128}", message = "Only uppercase and lowercase characters allowed!")
    private String partName;

    @NotNull(message = "This field is required!")
    @Size(min = 1, max = 9, message = "The partPrice should be until 9 digits!")
    @Pattern(regexp = "^[1-9]|[1-9][0-9]{1,9}", message = "The part price must contain only digits!")
    private String partPrice;

    public Long getPartID() {
        return partID;
    }

    public void setPartID(Long partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(String partPrice) {
        this.partPrice = partPrice;
    }
}
