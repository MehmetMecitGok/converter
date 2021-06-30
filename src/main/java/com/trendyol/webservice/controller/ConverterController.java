package com.trendyol.webservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.webservice.service.ConverterService;

/**
 * @author Mehmet
 *
 */

@RestController
@RequestMapping(value = "/api/converter")
public class ConverterController {
	private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

	@Autowired
	private ConverterService converterService;

	@PostMapping(value = "/webUrlToDeeplink", produces = "application/json")
	public ResponseEntity<String> convertWebUrlToDeeplink(@RequestBody String requestBody) {
		logger.debug("convertWebUrlToDeeplink is started");

		String convertedDeepLink = converterService.convertWebUrlToDeeplink(requestBody);

		logger.debug("convertWebUrlToDeeplink is finished");
		return new ResponseEntity<>(convertedDeepLink, HttpStatus.OK);
	}

	@PostMapping(value = "/deeplinkToWebUrl", produces = "application/json")
	public ResponseEntity<String> convertDeeplinkToWebUrl(@RequestBody String requestBody) {
		logger.debug("convertDeeplinkToWebUrl is started");

		String convertedWebUrl = converterService.convertDeeplinkToWebUrl(requestBody);

		logger.debug("convertDeeplinkToWebUrl is finished");
		return new ResponseEntity<>(convertedWebUrl, HttpStatus.OK);
	}

}
