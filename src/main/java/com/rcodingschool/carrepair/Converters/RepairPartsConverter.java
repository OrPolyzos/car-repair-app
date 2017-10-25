package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.RepairPart;
import com.rcodingschool.carrepair.Model.RepairPartForm;


public class RepairPartsConverter {

    public static RepairPart buildRepairPartObject(RepairPartForm repairPartForm) {

        RepairPart repairPart = new RepairPart();
        repairPart.setRepairID(repairPartForm.getRepairID());
        repairPart.setPartID(Long.valueOf(repairPartForm.getPartID()));
        repairPart.setQuantity(Integer.valueOf(repairPartForm.getQuantity()));
        return repairPart;

    }

}
