package model;

import java.io.Serializable;

/**
 * Created by json2pojo
 */
public class School implements Serializable {

	private static final long serialVersionUID = -2390425436172680486L;
	private String id;
	private String subject;
	private XingDate endDate;
	private String degree;
	private String name;
	private XingDate beginDate;
	private String notes;

	
	
	
	public School( String subject, XingDate endDate, String degree, String name, XingDate beginDate,
			String notes) {
		super();
		
		this.subject = subject;
		this.endDate = endDate;
		this.degree = degree;
		this.name = name;
		this.beginDate = beginDate;
		this.notes = notes;
	}

	public String getId() {
		return id;
	}

	public String getSubject() {
		return subject;
	}

	public XingDate getEndDate() {
		return endDate;
	}

	public String getDegree() {
		return degree;
	}

	public String getName() {
		return name;
	}

	public XingDate getBeginDate() {
		return beginDate;
	}

	public String getNotes() {
		return notes;
	}

}
