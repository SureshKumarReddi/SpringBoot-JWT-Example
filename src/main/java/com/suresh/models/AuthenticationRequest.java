package com.suresh.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {
	
	private static final long serialVersionUID = 5584840145116961126L;
	private String username;
	private String password;

}
