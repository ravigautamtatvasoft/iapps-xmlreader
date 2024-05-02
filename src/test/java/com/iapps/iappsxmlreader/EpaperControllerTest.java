package com.iapps.iappsxmlreader;

import com.iapps.iappsxmlreader.controller.EpaperController;
import com.iapps.iappsxmlreader.dto.EpaperDTO;
import com.iapps.iappsxmlreader.model.Epaper;
import com.iapps.iappsxmlreader.service.EpaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EpaperControllerTest {
    private MockMvc mockMvc;

    @Mock
    private EpaperService epaperService;

    @InjectMocks
    private EpaperController epaperController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(epaperController).build();
    }

    @Test
    void testUploadXml_ValidXmlFile() throws Exception {
        Path xmlFilePath = new ClassPathResource("iapps-file.xml").getFile().toPath();
        String xmlContent = new String(Files.readAllBytes(xmlFilePath));

        MockMultipartFile mockFile = new MockMultipartFile("paperXml", "iapps-file.xml",
                MediaType.APPLICATION_XML_VALUE, xmlContent.getBytes());

        EpaperService mockedService = mock(EpaperService.class);
        when(mockedService.uploadXml(any(), any())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(multipart("/api/uploadXml")
                        .file(mockFile))
                .andExpect(status().isOk());
    }
}
