package com.prueba.tecnica.adapter.out.persistence;

import com.prueba.tecnica.domain.UserData;

public class PersonalDataMapper {

    public static UserData entityToDomain(UsersEntity personalDataEntity) {
        return UserData.builder()
                .fullName(personalDataEntity.getFullName())
                .phone(personalDataEntity.getPhone())
                .address(personalDataEntity.getAddress())
                .build();
    }
}
