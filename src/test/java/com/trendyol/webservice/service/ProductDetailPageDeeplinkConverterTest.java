package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductDetailPageDeeplinkConverterTest {

	@InjectMocks
	private ProductDetailPageDeeplinkConverter productDetailPageDeeplinkConverter;

	@Test
	public void convertProductDetailPageDeeplinkConverter() {
		String deeplink = "ty://?Page=Product&ContentId=1925865";

		String webUrl = productDetailPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865");
	}

	@Test
	public void convertProductDetailPageDeeplinkConverterCase2() {
		String webUrl = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";

		String deeplink = productDetailPageDeeplinkConverter.converter(webUrl);

		assertThat(deeplink)
				.isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
	}

	@Test
	public void convertProductDetailPageDeeplinkConverterCase3() {
		String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";

		String webUrl = productDetailPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");
	}

	@Test
	public void convertProductDetailPageDeeplinkConverterCase4() {
		String deeplink = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";

		String webUrl = productDetailPageDeeplinkConverter.converter(deeplink);

		assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064");
	}

}