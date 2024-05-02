package com.iapps.iappsxmlreader.controller;

import com.iapps.iappsxmlreader.service.EpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class EpaperController {

    @Autowired
    private EpaperService epaperService;


    @PostMapping("/uploadXml")
    public ResponseEntity<?> uploadPaper(HttpServletRequest request, @RequestParam MultipartFile paperXml)
            throws IOException, SAXException, JAXBException {
        return epaperService.uploadXml(request, paperXml);
    }

    @GetMapping("/getEpapers")
    public ResponseEntity<?> getPapers(HttpServletRequest request,
                                       @RequestParam(value = "fromDate", required = false) String fromDate,
                                       @RequestParam(value = "toDate", required = false) String toDate,
                                       @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                       @RequestParam(value = "order", defaultValue = "false", required = false) Boolean order,
                                       @RequestParam(value = "search", defaultValue = "", required = false) String search) throws Exception {
        return epaperService.getAllEpaperList(request, search, sortBy, order, fromDate, toDate, pageNo, pageSize);
    }
}
