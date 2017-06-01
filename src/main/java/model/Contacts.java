package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

/** 
*
*/
@Entity
public class Contacts implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
    private Integer total;
    @Type(type="model.XingProfile")
    private List<XingProfile> users;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<XingProfile> getUsers() {
        return users;
    }

    public void setUsers(List<XingProfile> users) {
        this.users = users;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
}
