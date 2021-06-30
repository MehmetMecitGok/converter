package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchPageWebUrlConverterTest {

	@InjectMocks
	private SearchPageWebUrlConverter searchPageWebUrlConverter;

	@Test
	public void convertSearchPageWebUrlConverter() {
		String webUrl = "https://www.trendyol.com/sr?q=elbise";

		String deeplink = searchPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Search&Query=elbise");
	}

	@Test
	public void convertSearchPageWebUrlConverterCase2() {
		String webUrl = "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC";

		String deeplink = searchPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
	}

}