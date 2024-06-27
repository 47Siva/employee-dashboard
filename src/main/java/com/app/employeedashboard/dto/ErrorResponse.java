package com.app.employeedashboard.dto;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
	private String code;
	private String reason;
	private List<String> errorList;

	@Override
	public String toString() {
		return "Error [code=" + code + ", Reason=" + reason + "]";
	}
}
