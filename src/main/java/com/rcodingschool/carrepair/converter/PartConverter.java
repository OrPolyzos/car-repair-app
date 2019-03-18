package com.rcodingschool.carrepair.converter;

import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.model.PartForm;

public class PartConverter {

    public static Part partFormToPart(PartForm partForm) {
        Part part = new Part();
        part.setPartName(partForm.getPartName());
        part.setPartPrice(Long.valueOf(partForm.getPartPrice()));
        part.setId(partForm.getPartID());
        return part;
    }

    public static PartForm partToPartForm(Part part) {
        PartForm partForm = new PartForm();
        partForm.setPartID(part.getId());
        partForm.setPartName(part.getPartName());
        partForm.setPartPrice(String.valueOf(part.getPartPrice()));
        return partForm;
    }
}
