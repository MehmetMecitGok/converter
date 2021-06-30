package com.trendyol.webservice.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.trendyol.webservice.service.ConverterService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ConverterController.class)
public class ConverterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConverterService converterService;

	@Test
	public void convertWebUrlToDeeplink() throws Exception {
		mockMvc.perform(post("/api/converter/webUrlToDeeplink").contentType(MediaType.APPLICATION_JSON).content("https://www.trendyol.com/casio/saat-e-1925865?=439892&merchanitId_105064"))
				.andExpect(status().isOk());

		verify(converterService).convertWebUrlToDeeplink("https://www.trendyol.com/casio/saat-e-1925865?=439892&merchanitId_105064");
	}
	
	@Test
	public void convertDeeplinkToWebUrl() throws Exception {
		mockMvc.perform(post("/api/converter/deeplinkToWebUrl").contentType(MediaType.APPLICATION_JSON).content("ty://?Page=Favorites"))
				.andExpect(status().isOk());

		verify(converterService).convertDeeplinkToWebUrl("ty://?Page=Favorites");
	}
}