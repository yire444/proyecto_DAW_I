package com.nova.talentnova.controller;

import com.nova.talentnova.GeneralStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class GeneralStatusBooleanConverter implements AttributeConverter<GeneralStatus, Boolean> {

    @Override
    public Boolean convertToDatabaseColumn(GeneralStatus status) {
        if (status == null) {
            return true;
        }
        return status == GeneralStatus.ACTIVE;
    }

    @Override
    public GeneralStatus convertToEntityAttribute(Boolean dbData) {
        if (dbData == null || dbData) {
            return GeneralStatus.ACTIVE;
        }
        return GeneralStatus.INACTIVE;
    }
}