package com.iapps.iappsxmlreader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "epaperRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "epaperRequest")
public class EpaperRequestDTO {

	@XmlElementRef(name = "deviceInfo")
	private DeviceInfoTag deviceInfo;

	@XmlElementRef(name = "getPages")
	private GetPagesTag getPages;
}