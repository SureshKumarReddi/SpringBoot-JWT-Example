package com.suresh.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 8163318127932469266L;

	private final String jwt;

}
