package com.prueba.tecnica.adapter.in.web;

import com.prueba.tecnica.application.port.in.ObtainPersonalDataPort;
import com.prueba.tecnica.application.port.in.SavePersonalDataPort;
import com.prueba.tecnica.common.WebAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@WebAdapter
@RestController
public class PersonalTransferController {


    private final ObtainPersonalDataPort obtainPersonalDataPort;

    private final SavePersonalDataPort savePersonalDataPort;

    public PersonalTransferController(ObtainPersonalDataPort obtainPersonalDataPort,
                                      SavePersonalDataPort savePersonalDataPort) {
        this.obtainPersonalDataPort = obtainPersonalDataPort;
        this.savePersonalDataPort = savePersonalDataPort;
    }

    @GetMapping("/api/users")
    public ResponseEntity<byte[]> getPersonalData() {
        List<String> data = obtainPersonalDataPort.loadPersonalData();

        String content = String.join("\n", data);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header("Content-Disposition", "attachment; filename=data.txt")
                .body(content.getBytes());
    }


    @PostMapping(value ="/api/copy", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> postPersonalData(@RequestParam("file") MultipartFile file) throws IOException {

        savePersonalDataPort.savePersonalData(file);
        return ResponseEntity.ok().build();
    }


}
