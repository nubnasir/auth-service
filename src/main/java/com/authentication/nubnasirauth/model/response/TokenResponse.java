package com.authentication.nubnasirauth.model.response;

import java.io.Serializable;

public class TokenResponse implements Serializable {
	private final String token;
	public TokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}