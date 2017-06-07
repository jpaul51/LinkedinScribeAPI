package model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Comments", schema="public")
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="commentsId")
	long id;
	public String adminName;

	
	 @ElementCollection
	public List<String> commentList;
	
	
	
	
	public Comments() {
		super();
	}
	public Comments(long id, String adminName, List<String> commentList) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.commentList = commentList;
	}
	public Comments(String adminName, List<String> commentList) {
		super();
		this.adminName = adminName;
		this.commentList = commentList;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public List<String> getCommentList() {
		return commentList;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCommentList(List<String> commentList) {
		this.commentList = commentList;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminName == null) ? 0 : adminName.hashCode());
		result = prime * result + ((commentList == null) ? 0 : commentList.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Comments other = (Comments) obj;
		if (adminName == null) {
			if (other.adminName != null)
				return false;
		} else if (!adminName.equals(other.adminName))
			return false;
		if (commentList == null) {
			if (other.commentList != null)
				return false;
		} else if (!commentList.equals(other.commentList))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comments [adminName=" + adminName + ", commentList=" + commentList + "]";
	}
	
	
	
	
	
}
