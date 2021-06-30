package com.trendyol.webservice.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.webservice.entity.LinkConverter;
import com.trendyol.webservice.exception.ConverterException;
import com.trendyol.webservice.repository.ConverterRepository;
import com.trendyol.webservice.util.Constants;

@Service
public class ConverterService {

	@Autowired
	private ProductDetailPageWebUrlConverter productDetailPageWebUrlConverter;
	@Autowired
	private ProductDetailPageDeeplinkConverter productDetailPageDeeplinkConverter;
	@Autowired
	private SearchPageWebUrlConverter searchPageWebUrlConverter;
	@Autowired
	private SearchPageDeeplinkConverter searchPageDeeplinkConverter;
	@Autowired
	private OtherPageWebUrlConverter otherPageWebUrlConverter;
	@Autowired
	private OtherPageDeeplinkConverter otherPageDeeplinkConverter;

	@Autowired
	private ConverterRepository converterRepository;

	public String convertWebUrlToDeeplink(String url) {
		Converter urlConverter = checkWebUrl(url);
		return convertAndSave(url, urlConverter);
	}

	public String convertDeeplinkToWebUrl(String url) {
		Converter urlConverter = checkDeepLink(url);
		return convertAndSave(url, urlConverter);
	}

	private String convertAndSave(String url, Converter urlConverter) {
		String convertedUrl = urlConverter.converter(url);

		converterRepository.save(new LinkConverter(url, convertedUrl));

		return convertedUrl;
	}

	private Converter checkWebUrl(String url) {

		List<String> searchParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams()
				.get(Constants.SEARCH_QUERY_PARAMETER);

		if (Objects.requireNonNull(UriComponentsBuilder.fromUriString(url).build().getPath())
				.contains(Constants.PRODUCT_URL)) {
			return productDetailPageWebUrlConverter;
		} else if (!CollectionUtils.isEmpty(searchParameter)) {
			return searchPageWebUrlConverter;
		} else if (Objects.requireNonNull(UriComponentsBuilder.fromUriString(url).build().getPath())
				.contains(Constants.MY_ACCOUNT)) {
			return otherPageWebUrlConverter;
		} else {
			throw new ConverterException();
		}

	}

	private Converter checkDeepLink(String url) {

		List<String> pageParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams()
				.get(Constants.PAGE_PARAMETER);
		List<String> contentIdParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams()
				.get(Constants.CONTENT_ID_PARAMETER);
		List<String> queryParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams()
				.get(Constants.DEEPLINK_SEARCH_QUERY_PARAMETER);

		if (!CollectionUtils.isEmpty(pageParameter) && !CollectionUtils.isEmpty(contentIdParameter)) {
			return productDetailPageDeeplinkConverter;
		} else if (!CollectionUtils.isEmpty(pageParameter) && !CollectionUtils.isEmpty(queryParameter)) {
			return searchPageDeeplinkConverter;
		} else if (!CollectionUtils.isEmpty(pageParameter)) {
			return otherPageDeeplinkConverter;
		} else {
			throw new ConverterException();
		}

	}

}
