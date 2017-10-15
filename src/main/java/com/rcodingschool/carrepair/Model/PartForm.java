package com.rcodingschool.carrepair.Model;


import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PartForm {

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long repairId;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Long partID;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Short quantity;

    public Long getRepairId() { return repairId; }

    public void setRepairId(Long repairId) { this.repairId = repairId; }

    public Long getPartID() { return partID; }

    public void setPartID(Long partID) { this.partID = partID; }

    public Short getQuantity() { return quantity; }

    public void setQuantity(Short quantity) { this.quantity = quantity; }
}
