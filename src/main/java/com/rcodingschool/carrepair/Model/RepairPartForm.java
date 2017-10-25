package com.rcodingschool.carrepair.Model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class RepairPartForm {

    private Long repairID;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[1-9]|[1-9][0-9]{1,9}", message = "The partID must be equal or greater than 1!")
    private String partID;

    @NotNull(message = "This field is required!")
    @Pattern(regexp = "^[1-9]|[1-9][0-9]{1,9}", message = "The quantity must be equal or greater than 1!")
    private String Quantity;

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
