package com.rcodingschool.carrepair.converter;


import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.model.RepairForm;


public class RepairConverter {

    public static Repair repairFormToRepair(RepairForm repairForm) {
        Repair repair = new Repair();
        repair.setRepairTypeID(repairForm.getRepairTypeID());
        repair.setRepairDateTime(repairForm.getRepairDateTime());
        repair.setRepairStatus(repairForm.getRepairStatus());
        repair.setRepairTasks(repairForm.getRepairTasks());
        repair.setVehicleID(repairForm.getRepairVehicleID());
        repair.setId(repairForm.getRepairID());
        return repair;
    }

    public static RepairForm repairToRepairForm(Repair repair) {
        RepairForm repairForm = new RepairForm();
        repairForm.setRepairID(repair.getId());
        repairForm.setRepairDateTime(repair.getRepairDateTime());
        repairForm.setRepairStatus(repair.getRepairStatus());
        repairForm.setRepairTypeID(repair.getRepairTypeID());
        repairForm.setRepairTasks(repair.getRepairTasks());
        repairForm.setRepairVehicleID(repair.getVehicleID());
        return repairForm;
    }
}
