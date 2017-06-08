package model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;


@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String id;
	private String name;
	private String password;
	private String mail;
	
	 @ElementCollection(fetch = FetchType.LAZY)
	 @OneToMany(cascade=javax.persistence.CascadeType.ALL)
	 @Type(type="java.util.List")
	List<Favoris> fav;
	
	
	public Admin() {
		super();
	}



	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	

	public Admin(String name, String password, List<Favoris> fav) {
		super();
		this.name = name;
		this.password = password;
		this.fav = fav;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	








	public List<Favoris> getFav() {
		return fav;
	}



	public void setFav(List<Favoris> fav) {
		this.fav = fav;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", Name=" + name + ", password=" + password + "]";
	}
	
	
	
}
