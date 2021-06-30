package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OtherPageWebUrlConverterTest {

	@InjectMocks
	private OtherPageWebUrlConverter otherPageWebUrlConverter;

	@Test
	public void convertOtherPageWebUrlConverter() {
		String webUrl = "https://www.trendyol.com/Hesabim/Favoriler";

		String deeplink = otherPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Home");
	}

	@Test
	public void convertOtherPageWebUrlConverterForAnotherCase() {
		String webUrl = "https://www.trendyol.com/Hesabim/#/Siparislerim";

		String deeplink = otherPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Home");
	}
}