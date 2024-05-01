package com.iapps.iappsxmlreader.Utils;

import com.iapps.iappsxmlreader.dto.EpaperRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class XmlUtils {

    private Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    public boolean validateXml(MultipartFile xml) throws SAXException {
        if (xml.getContentType().contains("text/xml") || xml.getContentType().contains("application/xml")) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(getFile("epaper-schema.xsd"));
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            try {
                InputStream xmlInputStream = xml.getInputStream();
                validator.validate(new StreamSource(xmlInputStream));
            } catch (IOException | SAXException e) {
                LOGGER.error("XML is not valid", e);
                return false;
            }
        }
        LOGGER.info("XML validation passed");
        return true;
    }

    public EpaperRequestDTO parseXMLDocument(InputStream xmlInputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(EpaperRequestDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        EpaperRequestDTO epaperRequestDto = (EpaperRequestDTO) unmarshaller.unmarshal(xmlInputStream);
        LOGGER.info("XML parsed successfully");
        return epaperRequestDto;
    }

    private File getFile(String fileName) {
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }
}
