package com.trendyol.webservice.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.trendyol.webservice.util.Constants;

@Component
public class ProductDetailPageDeeplinkConverter implements Converter {

	@Override
	public String converter(String link) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(link);
		String contentId = uriComponentsBuilder.build().getQueryParams().get(Constants.CONTENT_ID_PARAMETER).get(0);

		String urls = UriComponentsBuilder.newInstance().scheme(Constants.HTTPS).host(Constants.WEB_URL_HOST)
				.path("/brand/name-p-{contentId}").buildAndExpand(contentId).toUriString();

		List<String> campaignParameterList = uriComponentsBuilder.build().getQueryParams()
				.get(Constants.CAMPAIGN_ID_PARAMETER);
		List<String> merchantParameterList = uriComponentsBuilder.build().getQueryParams()
				.get(Constants.DEEPLINK_MERCHANT_ID_PARAMETER);

		UriComponentsBuilder convertedComponentBuilder = UriComponentsBuilder.fromUriString(urls);

		if (!CollectionUtils.isEmpty(campaignParameterList)) {
			convertedComponentBuilder.replaceQueryParam(Constants.CAMPAIGN_ID_PARAMETER)
					.replaceQueryParam(Constants.BOUTIQUE_ID_PARAMETER, campaignParameterList.get(0));
		}

		if (!CollectionUtils.isEmpty(merchantParameterList)) {
			convertedComponentBuilder.replaceQueryParam(Constants.DEEPLINK_MERCHANT_ID_PARAMETER)
					.replaceQueryParam(Constants.MERCHANT_ID_PARAMETER, merchantParameterList.get(0));
		}

		return convertedComponentBuilder.toUriString();
	}

}
