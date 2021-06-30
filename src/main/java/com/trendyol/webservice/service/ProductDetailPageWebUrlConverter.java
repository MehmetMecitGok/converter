package com.trendyol.webservice.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.webservice.util.Constants;

@Component
public class ProductDetailPageWebUrlConverter implements Converter {

	@Override
	public String converter(String link) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(link);

		String productId = Objects.requireNonNull(UriComponentsBuilder.fromUriString(link).build().getPath())
				.split(Constants.PRODUCT_URL)[1];

		String urls = UriComponentsBuilder.newInstance().host(Constants.DEEPLINK_HOST).path("/")
				.queryParam(Constants.PAGE_PARAMETER, Constants.PRODUCT_DETAIL_PAGE_PARAMETER_VALUE)
				.query("{keyword}={keyword}").buildAndExpand(Constants.CONTENT_ID_PARAMETER, productId).toUriString()
				.substring(2);

		UriComponentsBuilder convertedComponentBuilder = UriComponentsBuilder.fromUriString(urls);

		List<String> boutiqueParameterList = uriComponentsBuilder.build().getQueryParams()
				.get(Constants.BOUTIQUE_ID_PARAMETER);
		List<String> merchantParameterList = uriComponentsBuilder.build().getQueryParams()
				.get(Constants.MERCHANT_ID_PARAMETER);

		if (!CollectionUtils.isEmpty(boutiqueParameterList)) {
			convertedComponentBuilder.replaceQueryParam(Constants.BOUTIQUE_ID_PARAMETER)
					.replaceQueryParam(Constants.CAMPAIGN_ID_PARAMETER, boutiqueParameterList.get(0));
		}

		if (!CollectionUtils.isEmpty(merchantParameterList)) {
			convertedComponentBuilder.replaceQueryParam(Constants.MERCHANT_ID_PARAMETER)
					.replaceQueryParam(Constants.DEEPLINK_MERCHANT_ID_PARAMETER, merchantParameterList.get(0));
		}

		return convertedComponentBuilder.toUriString();
	}

}
