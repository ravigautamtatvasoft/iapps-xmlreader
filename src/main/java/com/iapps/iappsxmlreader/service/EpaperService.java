package com.iapps.iappsxmlreader.service;

import com.iapps.iappsxmlreader.model.Epaper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * EpaperService
 *
 * @author Ravi.Gautam
 * @since 02-May-2024
 * @version 1.0
 *
 */

public interface EpaperService {

    ResponseEntity<?> getAllEpaperList(HttpServletRequest request, String search, String sortBy, Boolean order, String fromDate, String toDate, Integer pageNumber, Integer pageSize) throws Exception;

    ResponseEntity<?> uploadXml(HttpServletRequest request, MultipartFile xmlFile) throws IOException, SAXException, JAXBException;
}
