package com.org.schedular.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.NotNull;

@JsonRootName(value = "response")
public class ServiceResponse <T,E> implements Serializable {

	private static final long serialVersionUID = -3129936796455323592L;
	private T result;
	private boolean status; 
	@NotNull private String code;
	private E error;
	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the error
	 */
	public E getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(E error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "ServiceResponse [result=" + result + ", status=" + status + ", code=" + code + ", error=" + error + "]";
	}
	
	

}
