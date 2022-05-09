package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

public interface HistoryService {
	public JsonObject importLeadHistory(MultipartFile multipartFile);

}
