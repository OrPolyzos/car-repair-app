package com.rcodingschool.carrepair.Converters;


import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Model.RepairForm;


public class RepairConverter {

    //Object repair using the constructor set on the Domain
    public static Repair buildInsertRepairObject(RepairForm repairForm) {
        Repair repair = new Repair();
        repair.setRepairTypeID(repairForm.getRepairTypeID());
        repair.setRepairDateTime(repairForm.getRepairDateTime());
        repair.setRepairStatus(repairForm.getRepairStatus());
        repair.setRepairTasks(repairForm.getRepairTasks());
        repair.setVehicleID(repairForm.getRepairVehicleID());
        repair.setRepairStatus(repairForm.getRepairStatus());
        return repair;
    }

    public static Repair buildUpdateRepairObject(RepairForm repairForm) {
        Repair repair = new Repair();
        //We need the ID to update
        repair.setRepairID(repairForm.getRepairID());
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
