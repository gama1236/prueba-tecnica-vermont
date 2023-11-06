package com.prueba.tecnica.adapter.in.web;

import com.prueba.tecnica.application.port.in.ObtainPersonalDataPort;
import com.prueba.tecnica.application.port.in.SavePersonalDataPort;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonalTransferController.class)
@RunWith(SpringRunner.class)
class PersonalTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  ObtainPersonalDataPort obtainPersonalDataPort;

    @MockBean
    private  SavePersonalDataPort savePersonalDataPort;

    @Test
    void getPersonalData() throws Exception {
        List<String> mockData = Arrays.asList("Data1", "Data2", "Data3");
        when(obtainPersonalDataPort.loadPersonalData()).thenReturn(mockData);

        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(header().string("Content-Disposition", "attachment; filename=data.txt"))
                .andExpect(content().string(String.join("\n", mockData)));

        verify(obtainPersonalDataPort, timeout(1)).loadPersonalData();
    }

    @Test
    void postPersonalData() throws Exception {

        // Arrange
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", MediaType.TEXT_PLAIN_VALUE, "Test Content".getBytes());

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/copy")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(savePersonalDataPort, times(1)).savePersonalData(file);
    }
}