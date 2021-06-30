package com.trendyol.webservice.service;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.webservice.util.Constants;

@Component
public class SearchPageDeeplinkConverter implements Converter {

	@Override
	public String converter(String link) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(link);
		String searchParameter = uriComponentsBuilder.build().getQueryParams()
				.get(Constants.DEEPLINK_SEARCH_QUERY_PARAMETER).get(0);

		return UriComponentsBuilder.newInstance().scheme(Constants.HTTPS).host(Constants.WEB_URL_HOST)
				.path("/sr").query("q={keyword}").buildAndExpand(searchParameter).toUriString();
	}

}
