package com.trendyol.webservice.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mehmet
 *
 */
@Entity
@Table(name = "link_converter")
public class LinkConverter implements Serializable {

	private static final long serialVersionUID = 8567322806577179795L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime createdDateTime = LocalDateTime.now();
	private String requestContent;
	private String responseContent;

	public LinkConverter(String requestContent, String responseContent) {
		super();
		this.requestContent = requestContent;
		this.responseContent = responseContent;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

}
