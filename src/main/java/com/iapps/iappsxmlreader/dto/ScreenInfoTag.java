package com.iapps.iappsxmlreader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "screenInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DeviceInfoTag.class)
public class ScreenInfoTag {

	@XmlAttribute
	private Long width;

	@XmlAttribute
	private Long height;

	@XmlAttribute
	private Long dpi;
}