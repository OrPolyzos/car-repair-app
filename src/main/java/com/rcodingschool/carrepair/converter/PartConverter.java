package com.rcodingschool.carrepair.converter;

import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.model.PartForm;

public class PartConverter {

    public static Part buildInsertPartObject(PartForm partForm) {
        return provideBasicPart(partForm);
    }

    private static Part provideBasicPart(PartForm partForm) {
        Part part = new Part();
        part.setPartName(partForm.getPartName());
        part.setPartPrice(Long.valueOf(partForm.getPartPrice()));
        return part;
    }

    public static Part buildUpdatePartObject(PartForm partForm) {
        Part part = provideBasicPart(partForm);
        part.setPartID(partForm.getPartID());
        return part;
    }

    public static PartForm buildPartFormObject(Part part) {
        PartForm partForm = new PartForm();
        partForm.setPartID(part.getPartID());
        partForm.setPartName(part.getPartName());
        partForm.setPartPrice(String.valueOf(part.getPartPrice()));
        return partForm;
    }
}
