package com.prueba.tecnica.application.usecases;

import com.prueba.tecnica.application.port.out.GetPersonalData;
import com.prueba.tecnica.domain.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class ObtainPersonalDataTest {

    private static final String RELATIVE_PATH = "C:/prueba";
    @Mock
    private  GetPersonalData getPersonalData;

    @InjectMocks
    private ObtainPersonalData obtainPersonalData;
    @Test
    void loadPersonalData() {
        List<UserData> mockList = List.of(new UserData(1L,"Juan", "123456789", "Calle 1"));

        when(getPersonalData.getPersonalData()).thenReturn(mockList);

        List<String> response =  obtainPersonalData.loadPersonalData();

        assertEquals(1, response.size());
        assertEquals("Juan 123456789 Calle 1", response.get(0));

    }

    @Test
    void savePersonalData() throws InterruptedException {

        String fileName = "test.txt";
        byte[] fileContent = "Test Content".getBytes();
        MockMultipartFile file = new MockMultipartFile("file", fileName, "text/plain", fileContent);

        assertDoesNotThrow(() -> obtainPersonalData.savePersonalData(file));
        Thread.sleep(1000);

        Path filePath = Paths.get(RELATIVE_PATH, fileName);
        assertTrue(Files.exists(filePath));

        FileSystemUtils.deleteRecursively(new File(RELATIVE_PATH +"/"+ fileName));
    }

    @Test
    public void testSavePersonalDataEmptyFile() {
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        IOException exception = assertThrows(IOException.class, () -> obtainPersonalData.savePersonalData(emptyFile));
        assertEquals("File is empty", exception.getMessage());
    }
}