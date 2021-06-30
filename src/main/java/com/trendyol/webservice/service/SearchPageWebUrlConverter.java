package com.trendyol.webservice.service;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import com.trendyol.webservice.util.Constants;

@Component
public class SearchPageWebUrlConverter implements Converter {

	@Override
	public String converter(String link) {
		String searchQuery = UriComponentsBuilder.fromUriString(link).build().getQueryParams()
				.get(Constants.SEARCH_QUERY_PARAMETER).get(0);

		String urls = UriComponentsBuilder.newInstance().host(Constants.DEEPLINK_HOST).path("/")
				.queryParam(Constants.PAGE_PARAMETER, Constants.SEARCH_PAGE_PARAMETER_VALUE)
				.query("{keyword}={keyword}").buildAndExpand(Constants.DEEPLINK_SEARCH_QUERY_PARAMETER, searchQuery)
				.toUriString().substring(2);

		UriComponentsBuilder convertedComponentBuilder = UriComponentsBuilder.fromUriString(urls).encode();

		return UriUtils.decode(convertedComponentBuilder.toUriString(), StandardCharsets.UTF_8);
	}

}
