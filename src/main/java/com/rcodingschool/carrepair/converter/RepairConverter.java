package com.rcodingschool.carrepair.converter;


import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.model.RepairForm;


public class RepairConverter {

    public static Repair buildInsertRepairObject(RepairForm repairForm) {
        return provideBasicRepair(repairForm);
    }

    public static Repair buildUpdateRepairObject(RepairForm repairForm) {
        Repair repair = provideBasicRepair(repairForm);
        repair.setRepairID(repairForm.getRepairID());
        return repair;
    }

    private static Repair provideBasicRepair(RepairForm repairForm) {
        Repair repair = new Repair();
        repair.setRepairTypeID(repairForm.getRepairTypeID());
        repair.setRepairDateTime(repairForm.getRepairDateTime());
        repair.setRepairStatus(repairForm.getRepairStatus());
        repair.setRepairTasks(repairForm.getRepairTasks());
        repair.setVehicleID(repairForm.getRepairVehicleID());
        return repair;
    }

    //Building the repairForm Object using the repair Object
    public static RepairForm buildRepairFormObject(Repair repair) {
        RepairForm repairForm = new RepairForm();
        repairForm.setRepairID(repair.getRepairID());
        repairForm.setRepairDateTime(repair.getRepairDateTime());
        repairForm.setRepairStatus(repair.getRepairStatus());
        repairForm.setRepairTypeID(repair.getRepairTypeID());
        repairForm.setRepairTasks(repair.getRepairTasks());
        repairForm.setRepairVehicleID(repair.getVehicleID());
        return repairForm;
    }


}
