package com.iapps.iappsxmlreader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "getPages")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(EpaperRequestDTO.class)
public class GetPagesTag {

	@XmlAttribute
	private Long editionDefId;

	@XmlAttribute
	private Date publicationDate;
}