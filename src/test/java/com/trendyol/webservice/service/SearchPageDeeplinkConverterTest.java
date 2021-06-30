package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchPageDeeplinkConverterTest {

	@InjectMocks
	private SearchPageDeeplinkConverter searchPageDeeplinkConverter;

	@Test
	public void convertSearchPageDeeplinkConverter() {
		String deeplink = "ty://?Page=Search&Query=elbise";

		String webUrl = searchPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com/sr?q=elbise");
	}

	@Test
	public void convertSearchPageDeeplinkConverterCase2() {
		String deeplink = "ty://?Page=Search&Query=%C3%BCt%C3%BC";

		String webUrl = searchPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC");
	}

}