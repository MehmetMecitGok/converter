package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OtherPageDeeplinkConverterTest {

	@InjectMocks
	private OtherPageDeeplinkConverter otherPageDeeplinkConverter;

	@Test
	public void convertOtherPageDeeplinkConverter() {
		String deeplink = "ty://?Page=Favorites";

		String webUrl = otherPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com");
	}

	@Test
	public void convertOtherPageDeeplinkConverterAnotherCase() {
		String deeplink = "ty://?Page=Orders";

		String webUrl = otherPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com");
	}
}