package web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveFavBody {

	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "password")
	public String password;
	
	@JsonProperty(value = "title")
	public String title;
	
	@JsonProperty(value = "profile_id")
	public String profileId;
	
	
}
