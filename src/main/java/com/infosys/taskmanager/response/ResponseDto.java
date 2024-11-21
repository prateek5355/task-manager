package com.infosys.taskmanager.response;

public class ResponseDto<T> {
	private String status;
	private String result;
	private ResponseErrorDto error;
	private T data;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public ResponseErrorDto getError() {
		return error;
	}

	public void setError(ResponseErrorDto error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setData(T data) {
		this.data = data;
	}
}
