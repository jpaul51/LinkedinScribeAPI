package web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavorisBody {
private static final long serialVersionUID = -1764970584520387975L;
	
	@JsonProperty(value = "name")
	public String name;
	
	@JsonProperty(value = "password")
	public String password;

	@JsonProperty(value = "title")
	public String title;
	
	@JsonProperty(value="profile_id_list")
	public List<String> profileId;
}
