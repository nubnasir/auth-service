package com.authentication.nubnasirauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StatusController {

	@RequestMapping({ "/status" })
	public String firstPage() {
		return "Authentication server is running";
	}

}