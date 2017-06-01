package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by json2pojo
 */
public class EducationalBackground implements Serializable {
 
	private static final long serialVersionUID = 7273795597965337052L;
	private School primarySchool;
	private List<String> qualifications;
	private List<School> schools;

	public List<String> getQualifications() {
		return qualifications;
	}

	public List<School> getSchools() {
		return schools;
	}

	public School getPrimarySchool() {
		return primarySchool;
	}

	public EducationalBackground(School primarySchool, List<String> qualifications, List<School> schools) {
		super();
		this.primarySchool = primarySchool;
		this.qualifications = qualifications;
		this.schools = schools;
	}

	
	
}
