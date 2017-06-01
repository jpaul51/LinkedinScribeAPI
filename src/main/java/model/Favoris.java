package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Favoris {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	String title;
	@ManyToMany
	List<XingProfile> favList;

	
	
	public Favoris(long id, String title, List<XingProfile> favList) {
		super();
		this.id = id;
		this.title = title;
		this.favList = favList;
	}

	public Favoris() {
		super();
	}

	public Favoris(List<XingProfile> favList) {
		super();
		this.favList = favList;
	}

	public Favoris(String title) {
		super();
		this.title = title;
	}

	public Favoris(String title, List<XingProfile> favList) {
		super();
		this.title = title;
		this.favList = favList;
	}

	public List<XingProfile> getFavList() {
		return favList;
	}

	public void setFavList(List<XingProfile> favList) {
		this.favList = favList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Favoris other = (Favoris) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Favoris [id=" + id + ", title=" + title + ", favList=" + favList + "]";
	}

	
	
	
}
