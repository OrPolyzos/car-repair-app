package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.Part;
import com.rcodingschool.carrepair.Model.PartForm;

public class PartConverter {

    public static Part buildPartObject(PartForm partForm) {
        Part part = new Part();
        part.setPartID(partForm.getPartID());
        part.setPartName(partForm.getPartName());
        part.setPartPrice(partForm.getPartPrice());
        return part;
    }

    public static PartForm buildPartFormObject(Part part) {
        PartForm partForm = new PartForm();
        partForm.setPartID(part.getPartID());
        partForm.setPartName(part.getPartName());
        partForm.setPartPrice(part.getPartPrice());
        return partForm;
    }
}
