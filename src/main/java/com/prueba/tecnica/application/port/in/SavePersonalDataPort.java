package com.prueba.tecnica.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SavePersonalDataPort {

    void savePersonalData(MultipartFile file) throws IOException;
}
