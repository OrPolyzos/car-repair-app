package com.rcodingschool.carrepair.converter;

import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.model.RepairPartForm;


public class RepairPartsConverter {

    public static RepairPart buildRepairPartObject(RepairPartForm repairPartForm) {
        RepairPart repairPart = new RepairPart();
        repairPart.setRepairID(repairPartForm.getRepairID());
        repairPart.setPartID(Long.valueOf(repairPartForm.getPartID()));
        repairPart.setQuantity(Integer.valueOf(repairPartForm.getQuantity()));
        return repairPart;
    }

}
