package com.rcodingschool.carrepair.Converters;


import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Domain.RepairType;
import com.rcodingschool.carrepair.Domain.Vehicle;
import com.rcodingschool.carrepair.Model.RepairForm;


public class RepairConverter {
//Object repair using the constructor set on the Domain
    public static Repair buildInsertRepairObject(RepairForm repairForm) {
        Repair repair = new Repair();
        RepairType repairType = new RepairType ();
        Vehicle vehicle = new Vehicle ();
        repair.setRepairDate(repairForm.getRepairDate());
        repair.setRepairTime(repairForm.getRepairTime());
        repair.setRepairStatus(repairForm.getRepairStatus());
        repair.setRepairTasks(repairForm.getRepairTasks());
        //repairType.setRepairTypeID (repairForm.getRepairTypeID ());
        repair.setRepairType (repairType);
        repair.setRepairTotalCost(repairForm.getRepairTotalCost());
        vehicle.setVehicleID(repairForm.getRepairVehicleID());
        repair.setVehicle (vehicle);
        return repair;
    }
//
//    public static RepairType buildInsertRepairTypeObject(RepairForm repairForm) {
//        RepairType repairType = new RepairType ();
//       // repairType.setRepairTypeID (repairForm.getRepairTypeID ());
//        repairType.setRepairType (repairForm.getRepairΤype ());
//        repairType.setFixedPrice (repairForm.getFixedPrice ());
//        return repairType;
//    }

    public static Repair buildUpdateRepairObject(RepairForm repairForm) {
        Repair repair = new Repair();
        //We need the ID to update
        repair.setRepairID(repairForm.getRepairID());
        repair.setRepairDate(repairForm.getRepairDate());
        repair.setRepairTime(repairForm.getRepairTime());
        repair.setRepairStatus(repairForm.getRepairStatus());
        repair.setRepairTasks(repairForm.getRepairTasks());
        repair.setRepairTotalCost(repairForm.getRepairTotalCost());
        repair.setVehicleID(repairForm.getRepairVehicleID());
        return repair;
    }


//    public static RepairType buildUpdateRepairTypeObject(RepairForm repairForm) {
//        RepairType repairType = new RepairType ();
//        repairType.setRepairTypeID (repairForm.getRepairTypeID ());
//        repairType.setRepairType (repairForm.getRepairΤype ());
//        repairType.setFixedPrice (repairForm.getFixedPrice ());
//        return repairType;
//    }

    //Building the repairForm Object using the repair Object
    public static RepairForm buildRepairFormObject(Repair repair) {
        //RepairType repairType)
        RepairForm repairForm = new RepairForm();
        repairForm.setRepairID (repair.getRepairID ());
        //Conversion --> from LocalDate to Date (below)
        // (Date.valueOf(repair.getRepairTime ()) in case we need repair.RepairTime to be LocalDate
        repairForm.setRepairDate (repair.getRepairDate ());
        //Conversion --> from LocalTime to Time (below)
        //Time.valueOf (repair.getRepairTime () in case we need repair.RepairTime to be LocalDate
        repairForm.setRepairTime (repair.getRepairTime ());
        repairForm.setRepairStatus (repair.getRepairStatus ());
        repairForm.setRepairTypeID (repair.getRepairTypeID ());
        repairForm.setRepairTasks (repair.getRepairTasks ());
        repairForm.setRepairVehicleID (repair.getVehicleID ());
        //repairForm.setRepairΤype (repairType.getRepairType ( ));
        //repairForm.setFixedPrice (repairType.getFixedPrice ());
        return repairForm;
    }


}
