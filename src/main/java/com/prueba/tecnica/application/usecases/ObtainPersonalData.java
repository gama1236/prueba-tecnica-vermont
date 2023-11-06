package com.prueba.tecnica.application.usecases;

import com.prueba.tecnica.application.port.in.ObtainPersonalDataPort;
import com.prueba.tecnica.application.port.in.SavePersonalDataPort;
import com.prueba.tecnica.application.port.out.GetPersonalData;
import com.prueba.tecnica.common.AdapterAsync;
import com.prueba.tecnica.common.UseCase;
import com.prueba.tecnica.domain.UserData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@UseCase
public class ObtainPersonalData implements ObtainPersonalDataPort, SavePersonalDataPort {

    private static final String RELATIVE_PATH = "C:/prueba";
    private final GetPersonalData getPersonalData;


    ObtainPersonalData(GetPersonalData getPersonalData){
        this.getPersonalData = getPersonalData;
    }

    /**
     * Method to get personal data
     * @return List<String>
     */
    @Override
    public List<String> loadPersonalData() {
        List<UserData> userData = getPersonalData.getPersonalData();
        List<String> personalDataString = new ArrayList<>();

        if (!userData.isEmpty()) {
            for (UserData data : userData) {
                String peronalDatastring  =   data.getFullName() + " " + data.getPhone() + " " + data.getAddress();
                personalDataString.add(peronalDatastring);
            }
        }

        return personalDataString;
    }

    /**
     * Method to save personal data
     * @param file
     * @throws IOException
     */
    @AdapterAsync
    @Override
    public void savePersonalData(MultipartFile file) throws IOException {

            if (file != null && !file.isEmpty() && file.getSize() > 0 && !file.getOriginalFilename().isEmpty()){
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(RELATIVE_PATH, fileName);
                file.transferTo(filePath.toFile());
            } else {
                throw  new IOException("File is empty");
            }

    }
}
