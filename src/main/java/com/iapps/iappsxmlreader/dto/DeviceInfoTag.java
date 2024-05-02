package com.iapps.iappsxmlreader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "deviceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(EpaperRequestDTO.class)
public class DeviceInfoTag {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String id;

	@XmlElementRef(name = "screenInfo")
	private ScreenInfoTag screenInfo;

	@XmlElementRef(name = "osInfo")
	private OsInfoTag osInfo;

	@XmlElementRef(name = "appInfo")
	private AppInfoTag appInfo;
}