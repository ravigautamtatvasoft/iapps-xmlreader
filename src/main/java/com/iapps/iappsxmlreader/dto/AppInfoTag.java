package com.iapps.iappsxmlreader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "appInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DeviceInfoTag.class)
public class AppInfoTag {

	@XmlElement
	private String newspaperName;

	@XmlElement
	private Float version;
}