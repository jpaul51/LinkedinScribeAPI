package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
@Entity
public class WebProfiles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@Type(type="java.lang.String")
    private List<String> qype;
	@Type(type="java.lang.String")
    private List<String> googlePlus;
	@Type(type="java.lang.String")
    private List<String> blog;
	@Type(type="java.lang.String")
    private List<String> homepage;


    public List<String> getQype() {
        return qype;
    }

    public void setQype(List<String> qype) {
        this.qype = qype;
    }

    public List<String> getGooglePlus() {
        return googlePlus;
    }
 
    public void setGooglePlus(List<String> googlePlus) {
        this.googlePlus = googlePlus;
    }

    public List<String> getBlog() {
        return blog;
    }

    public void setBlog(List<String> blog) {
        this.blog = blog;
    }

    public List<String> getHomepage() {
        return homepage;
    }

    public void setHomepage(List<String> homepage) {
        this.homepage = homepage;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}