package com.rcodingschool.carrepair.Converters;


import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Model.RepairForm;

public class RepairConverter {
//Object repair using the constructor set on the Domain
    public static Repair buildRepairObject(RepairForm repairForm) {
        return new Repair (repairForm.getRepairID (),repairForm.getRepairDate (),
                repairForm.getRepairTime () , repairForm.getStatus (), repairForm.getRepairtTypeID (),
                repairForm.getTasks (), repairForm.getTotalCost (), repairForm.getVehicleID ());
    }

    //Building the repairForm Object using the repair Object
    public static RepairForm buildRepairFormObject(Repair repair) {
        RepairForm repairForm = new RepairForm();
        repairForm.setRepairID (repair.getRepairID ());
        repairForm.setRepairDate (repair.getRepairDate ());
        repairForm.setRepairTime (repair.getRepairTime ());
        repairForm.setStatus (repair.getRepairStatus ());
        repairForm.setRepairtTypeID (repair.getRepairTypeID ());
        repairForm.setTasks (repair.getRepairTasks ());
        repairForm.setVehicleID (repair.getVehicleID ());
        return repairForm;
    }


}
