package com.iapps.iappsxmlreader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "osInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DeviceInfoTag.class)
public class OsInfoTag {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private Float version;
}