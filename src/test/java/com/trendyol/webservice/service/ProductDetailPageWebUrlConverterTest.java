package com.trendyol.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductDetailPageWebUrlConverterTest {

	@InjectMocks
	private ProductDetailPageWebUrlConverter productDetailPageWebUrlConverter;

	@Test
	public void convertProductDetailPageWebUrlConverter() {
		String webUrl = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";

		String deeplink = productDetailPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865");
	}

	@Test
	public void convertProductDetailPageWebUrlConverterCase2() {
		String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";

		String deeplink = productDetailPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
	}

	@Test
	public void convertProductDetailPageWebUrlConverterCase3() {
		String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892";

		String deeplink = productDetailPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892");
	}

	@Test
	public void convertProductDetailPageWebUrlConverterCase4() {
		String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?merchantId=105064";

		String deeplink = productDetailPageWebUrlConverter.converter(webUrl);

		assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&MerchantId=105064");
	}
}