package com.iapps.iappsxmlreader.service;

import com.iapps.iappsxmlreader.Utils.XmlUtils;
import com.iapps.iappsxmlreader.dto.EpaperDTO;
import com.iapps.iappsxmlreader.dto.EpaperRequestDTO;
import com.iapps.iappsxmlreader.model.Epaper;
import com.iapps.iappsxmlreader.repository.EpaperRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Epaper service implementation class.
 *
 * @author Ravi.Gautam
 * @see EpaperRepository
 * @see XmlUtils
 * @since 02-May-2024
 * @version 1.0
 *
 */

@Service
public class EpaperServiceImpl implements EpaperService{
    private Logger LOGGER = LoggerFactory.getLogger(EpaperServiceImpl.class);

    @Autowired
    private EpaperRepository epaperRepository;

    @Autowired
    private XmlUtils xmlUtils;


    /** This Service method is used to get the data by applying filters
     * @param request
     * @param fromDate
     * @param toDate
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param order
     * @param search
     * @return ResponseEntity
     * @throws Exception
     */
    @Override
    public ResponseEntity<?> getAllEpaperList(HttpServletRequest request, String search, String sortBy, Boolean order, Long fromDate, Long toDate, Integer pageNumber, Integer pageSize) throws Exception {

        LOGGER.info("In getAllEpaperList");
        Pageable page = PageRequest.of(pageNumber == null ? 0 : pageNumber,
                pageSize == null ? (Integer.MAX_VALUE - 1) : pageSize == Integer.MAX_VALUE ? (pageSize - 1) : pageSize,
                Sort.by(order == null || order ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy == null ? "id" : sortBy));

        Date dateFrom = fromDate == null ? new Date(0) : new Date(fromDate);
        Date dateTo = toDate == null ? new Date() : new Date(toDate);

        LOGGER.info("fromDate timestamp:"+dateFrom+" toDate timestamp:"+toDate);

        List<Epaper> epapers = epaperRepository.getAllEpaperList(search, dateFrom, dateTo, page);
        return ResponseEntity.ok(epapers.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    /** This service method  is used to validate the xml and process to save the XML data.
     * @param request
     * @param xmlFile
     * @return ResponseEntity
     * @throws IOException
     * @throws SAXException
     * @throws JAXBException
     */
    @Override
    public ResponseEntity<?> uploadXml(HttpServletRequest request, MultipartFile xmlFile) throws IOException, SAXException, JAXBException {
        LOGGER.info("Upload XML Start");
        Boolean validateXML = xmlUtils.validateXml(xmlFile);
        if(validateXML){
            EpaperRequestDTO epaperRequestDTO = xmlUtils.parseXMLDocument(xmlFile.getInputStream());
            Epaper epaper = toEntity(xmlFile.getOriginalFilename(), epaperRequestDTO);
            epaper = epaperRepository.save(epaper);
            return ResponseEntity.ok(toDTO(epaper));
        }
        return ResponseEntity.badRequest().body("Invalid File");
    }

    /** It converts the epaper to epaperDTO
     * @param epaper
     * @return EpaperDTO
     */
    private EpaperDTO toDTO(Epaper epaper) {
        EpaperDTO epaperDto = new EpaperDTO();
        BeanUtils.copyProperties(epaper, epaperDto);
        return epaperDto;
    }

    /** It converts the EpaperRequestDTO to Epaper
     * @param filename
     * @param epaperRequestDTO
     * @return Epaper
     */
    private Epaper toEntity(String filename, EpaperRequestDTO epaperRequestDTO) {
        Epaper epaper = Epaper.builder().id(null).filename(filename)
                .newspaperName(epaperRequestDTO.getDeviceInfo().getAppInfo().getNewspaperName())
                .height(epaperRequestDTO.getDeviceInfo().getScreenInfo().getHeight())
                .width(epaperRequestDTO.getDeviceInfo().getScreenInfo().getWidth())
                .dpi(epaperRequestDTO.getDeviceInfo().getScreenInfo().getDpi()).uploadedAt(new Date()).build();
        return epaper;
    }
}
