package com.rcodingschool.carrepair.Converters;


import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Model.RepairForm;


public class RepairConverter {
//Object repair using the constructor set on the Domain
    public static Repair buildInsertRepairObject(RepairForm repairForm) {
        Repair repair = new Repair();
        repair.setRepairDate(repairForm.getRepairDate());
        repair.setRepairTime(repairForm.getRepairTime());
        repair.setRepairStatus(repairForm.getStatus());
        repair.setRepairTasks(repairForm.getTasks());
        repair.setRepairTotalCost(repairForm.getTotalCost());
        repair.setVehicleID(repairForm.getVehicleID());
        return repair;
    }

    public static Repair buildUpdateRepairObject(RepairForm repairForm) {
        Repair repair = new Repair();
        //We need the ID to update
        repair.setRepairID(repairForm.getRepairID());
        repair.setRepairDate(repairForm.getRepairDate());
        repair.setRepairTime(repairForm.getRepairTime());
        repair.setRepairStatus(repairForm.getStatus());
        repair.setRepairTasks(repairForm.getTasks());
        repair.setRepairTotalCost(repairForm.getTotalCost());
        repair.setVehicleID(repairForm.getVehicleID());
        return repair;
    }

    //Building the repairForm Object using the repair Object
    public static RepairForm buildRepairFormObject(Repair repair) {
        RepairForm repairForm = new RepairForm();
        repairForm.setRepairID (repair.getRepairID ());
        //Conversion --> from LocalDate to Date (below)
        // (Date.valueOf(repair.getRepairTime ()) in case we need repair.RepairTime to be LocalDate
        repairForm.setRepairDate (repair.getRepairDate ());
        //Conversion --> from LocalTime to Time (below)
        //Time.valueOf (repair.getRepairTime () in case we need repair.RepairTime to be LocalDate
        repairForm.setRepairTime (repair.getRepairTime ());
        repairForm.setStatus (repair.getRepairStatus ());
        repairForm.setRepairTypeID (repair.getRepairTypeID ());
        repairForm.setTasks (repair.getRepairTasks ());
        repairForm.setVehicleID (repair.getVehicleID ());
        return repairForm;
    }


}
