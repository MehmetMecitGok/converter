package com.trendyol.webservice.service;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.webservice.util.Constants;

@Component
public class OtherPageDeeplinkConverter implements Converter {

	@Override
	public String converter(String link) {
		return UriComponentsBuilder.newInstance().scheme(Constants.HTTPS).host(Constants.WEB_URL_HOST).build()
				.toUriString();
	}

}
