package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TimeZone {
	
	@GeneratedValue
	@Id
	long id;
    private String name;
    private Double utcOffset;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Double utcOffset) {
        this.utcOffset = utcOffset;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}