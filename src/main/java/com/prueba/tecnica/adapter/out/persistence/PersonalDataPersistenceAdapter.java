package com.prueba.tecnica.adapter.out.persistence;

import com.prueba.tecnica.application.port.out.GetPersonalData;
import com.prueba.tecnica.common.PersistenceAdapter;
import com.prueba.tecnica.domain.PersonalData;

import java.util.List;

@PersistenceAdapter
public class PersonalDataPersistenceAdapter implements GetPersonalData {

    private SpringPersonalDataRepository personalDataRepository;

    public PersonalDataPersistenceAdapter(SpringPersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Override
    public List<PersonalData> getPersonalData() {
        return personalDataRepository.findAll().stream().map(PersonalDataMapper::entityToDomain).toList();
    }
}
