package net.saisimon.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.saisimon.env.ConfigProperties;

@Builder
@Getter
@Setter
@ToString
public class Result implements Serializable {
	
	private static final long serialVersionUID = 4569462637678567329L;
	
	/**
	 * @see ConfigProperties#SUCCESS
	 * @see ConfigProperties#REQUEST_ERROR
	 * @see ConfigProperties#SERVER_ERROR
	 */
	private Integer code;
	private Object result;
	private String message;
	private String errorMessage;
	
	public boolean isSuccess() {
		return code != null && code == ConfigProperties.SUCCESS;
	}
	
}
