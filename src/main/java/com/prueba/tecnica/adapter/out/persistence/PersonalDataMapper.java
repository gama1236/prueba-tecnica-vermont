package com.prueba.tecnica.adapter.out.persistence;

import com.prueba.tecnica.domain.PersonalData;

public class PersonalDataMapper {

    public static PersonalData entityToDomain(PersonalDataEntity personalDataEntity) {
        return PersonalData.builder()
                .fullName(personalDataEntity.getFullName())
                .phone(personalDataEntity.getPhone())
                .address(personalDataEntity.getAddress())
                .build();
    }
}
