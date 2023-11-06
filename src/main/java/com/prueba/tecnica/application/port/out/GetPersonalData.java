package com.prueba.tecnica.application.port.out;

import com.prueba.tecnica.domain.UserData;

import java.util.List;

public interface GetPersonalData {
    List<UserData> getPersonalData();
}
