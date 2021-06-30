package com.trendyol.webservice.service;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.webservice.util.Constants;

@Component
public class OtherPageWebUrlConverter implements Converter {

	@Override
	public String converter(String link) {
		return UriComponentsBuilder.newInstance().host(Constants.DEEPLINK_HOST).path("/")
				.queryParam(Constants.PAGE_PARAMETER, Constants.HOME_PARAMETER_VALUE).build().toUriString()
				.substring(2);
	}

}
