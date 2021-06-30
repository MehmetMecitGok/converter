package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trendyol.webservice.exception.ConverterException;
import com.trendyol.webservice.repository.ConverterRepository;

@ExtendWith(MockitoExtension.class)
class ConverterServiceTest {

	@InjectMocks
	private ConverterService converterService;

	@Mock
	private ProductDetailPageWebUrlConverter productDetailPageWebUrlConverter;
	@Mock
	private ProductDetailPageDeeplinkConverter productDetailPageDeeplinkConverter;
	@Mock
	private SearchPageWebUrlConverter searchPageWebUrlConverter;
	@Mock
	private SearchPageDeeplinkConverter searchPageDeeplinkConverter;
	@Mock
	private OtherPageWebUrlConverter otherPageWebUrlConverter;
	@Mock
	private OtherPageDeeplinkConverter otherPageDeeplinkConverter;
	@Mock
	private ConverterRepository converterRepository;

	@Test
	public void convertProductDetailPageWebUrl() {
		String url = "https://www.trendyol.com/brand/name-p-1925865";
		String convertedUrl = "ty://?Page=Product&ContentId=1925865";

		given(productDetailPageWebUrlConverter.converter(url)).willReturn(convertedUrl);

		String convert = converterService.convertWebUrlToDeeplink(url);

		verify(productDetailPageWebUrlConverter).converter(url);
		assertThat(convert).isEqualTo(convertedUrl);
	}

	@Test
	public void convertProductDetailPageDeeplink() {
		String url = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
		String convertedUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";

		given(productDetailPageDeeplinkConverter.converter(url)).willReturn(convertedUrl);

		String convert = converterService.convertDeeplinkToWebUrl(url);

		verify(productDetailPageDeeplinkConverter).converter(url);
		assertThat(convert).isEqualTo(convertedUrl);
	}

	@Test
	public void convertSearchPageWebUrl() {
		String url = "https://www.trendyol.com/sr?q=elbise";
		String convertedUrl = "ty://?Page=Search&Query=elbise";

		given(searchPageWebUrlConverter.converter(url)).willReturn(convertedUrl);

		String convert = converterService.convertWebUrlToDeeplink(url);

		verify(searchPageWebUrlConverter).converter(url);
		assertThat(convert).isEqualTo(convertedUrl);
	}

	@Test
	public void convertSearchPageDeeplink() {
		String url = "ty://?Page=Search&Query=elbise";
		String convertedUrl = "https://www.trendyol.com/sr?q=elbise";

		given(searchPageDeeplinkConverter.converter(url)).willReturn(convertedUrl);

		String convert = converterService.convertDeeplinkToWebUrl(url);

		verify(searchPageDeeplinkConverter).converter(url);
		assertThat(convert).isEqualTo(convertedUrl);
	}

	@Test
	public void convertOtherPageWebUrl() {
		String url = "https://www.trendyol.com/Hesabim/Favoriler";
		String convertedUrl = "ty://?Page=Home";

		given(otherPageWebUrlConverter.converter(url)).willReturn(convertedUrl);

		String convert = converterService.convertWebUrlToDeeplink(url);

		verify(otherPageWebUrlConverter).converter(url);
		assertThat(convert).isEqualTo(convertedUrl);
	}

	@Test
	public void convertOtherPageDeeplink() {
		String url = "ty://?Page=Orders";
		String convertedUrl = "https://www.trendyol.com";

		given(otherPageDeeplinkConverter.converter(url)).willReturn(convertedUrl);

		String convert = converterService.convertDeeplinkToWebUrl(url);

		verify(otherPageDeeplinkConverter).converter(url);
		assertThat(convert).isEqualTo(convertedUrl);
	}

	@Test
	public void throwexceptionWhenWebUrlIsNotValid() {
		assertThatCode(() -> converterService.convertWebUrlToDeeplink("invalidUrl")).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Invalid Url");
	}
	
	@Test
	public void throwexceptionWhenDeeplinklIsNotValid() {
		assertThatCode(() -> converterService.convertDeeplinkToWebUrl("invalidUrl")).isInstanceOf(ConverterException.class)
				.hasMessageContaining("Invalid Url");
	}

}
