package web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginBody {
private static final long serialVersionUID = -1764970284520387975L;
	
	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "password")
	public String password;

}
