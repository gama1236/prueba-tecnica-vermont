package com.prueba.tecnica.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringPersonalDataRepository  extends JpaRepository<PersonalDataEntity, Long> {
}
