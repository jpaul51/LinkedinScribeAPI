/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model class containing a user's Xing profile information.
 *
 * @author Johannes Buehler
 * @author Dominik Bartholdi <domi@fortysix.ch>
 */
@Entity
@Table(name = "XingProfile", schema="public")
public class XingProfile implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="profileGeneratedId")
long idLong;
    private static final long serialVersionUID = 6579398072819111682L;
    @Type(type="java.lang.String")
    private List<String> badges;
    private BirthDate birthDate;
    private PhotoUrls photoUrls;
    private String interests;
    private String wants;
    private String organisationMember;
    private String gender;
    private String pageName;
    private BusinessAddress businessAddress;
    private PrivateAddress privateAddress;
    private String haves;
    @Column(name="prfileId")
    private String id;
    private String firstName;
    private String lastName;
    private String permalink;
    private String activeEmail;
    private String displayName;
    @ManyToOne
    private TimeZone timeZone;
    @Type(type="java.lang.String")
    private List<String> premiumServices;
    private EmploymentStatus employmentStatus;
    @ManyToOne
    private WebProfiles webProfiles;
    @ManyToOne
    private InstantMessagingAccounts instantMessagingAccounts;
    private ProfessionalExperience professionalExperience;
    private EducationalBackground educationalBackground;

    
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(value = EnumType.STRING)
    private Map<Language, LanguageSkill> languages;
    
    @Type(type="model.Contacts")
    @ManyToOne(cascade=CascadeType.ALL) 
    @JsonIgnore
    private Contacts contacts;

    @ElementCollection(fetch = FetchType.LAZY)
	 @OneToMany(cascade=javax.persistence.CascadeType.ALL)
	 @Type(type="java.util.List")
    List<Comments> comments;


    public XingProfile() {
    }

    public XingProfile(
            String id,
            String firstName,
            String lastName,
            String permalink,
            String activeEmail,
            String displayName
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permalink = permalink;
        this.activeEmail = activeEmail;
        this.displayName = displayName;
    }

    
    
  
    



	



	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public long getIdLong() {
		return idLong;
	}

	public void setIdLong(long idLong) {
		this.idLong = idLong;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void setActiveEmail(String activeEmail) {
		this.activeEmail = activeEmail;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setEmploymentStatus(EmploymentStatus employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public void setInstantMessagingAccounts(InstantMessagingAccounts instantMessagingAccounts) {
		this.instantMessagingAccounts = instantMessagingAccounts;
	}

	public void setProfessionalExperience(ProfessionalExperience professionalExperience) {
		this.professionalExperience = professionalExperience;
	}

	public void setEducationalBackground(EducationalBackground educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public void setLanguages(Map<Language, LanguageSkill> languages) {
		this.languages = languages;
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhotoUrls(PhotoUrls photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setWants(String wants) {
        this.wants = wants;
    }

    public void setOrganisationMember(String organisationMember) {
        this.organisationMember = organisationMember;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public void setBusinessAddress(BusinessAddress businessAddress) {
        this.businessAddress = businessAddress;
    }

    public void setHaves(String haves) {
        this.haves = haves;
    }

    public void setPrivateAddress(PrivateAddress privateAddress) {
        this.privateAddress = privateAddress;
    }

    public void setPremiumServices(List<String> premiumServices) {
        this.premiumServices = premiumServices;
    }

    public void setWebProfiles(WebProfiles webProfiles) {
        this.webProfiles = webProfiles;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public List<String> getBadges() {
        return badges;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public PhotoUrls getPhotoUrls() {
        return photoUrls;
    }

    public String getInterests() {
        return interests;
    }

    public String getWants() {
        return wants;
    }

    public String getOrganisationMember() {
        return organisationMember;
    }

    public String getGender() {
        return gender;
    }

    public String getPageName() {
        return pageName;
    }

    public BusinessAddress getBusinessAddress() {
        return businessAddress;
    }

    public PrivateAddress getPrivateAddress() {
        return privateAddress;
    }

    public String getHaves() {
        return haves;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getActiveEmail() {
        return activeEmail;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ProfessionalExperience getProfessionalExperience() {
        return professionalExperience;
    }

    public EducationalBackground getEducationalBackground() {
        return educationalBackground;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }
    @Enumerated
    public Map<Language, LanguageSkill> getLanguages() {
        return languages;
    }

    public InstantMessagingAccounts getInstantMessagingAccounts() {
        return instantMessagingAccounts;
    }

    public List<String> getPremiumServices() {
        return premiumServices;
    }

    public WebProfiles getWebProfiles() {
        return webProfiles;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idLong ^ (idLong >>> 32));
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
		XingProfile other = (XingProfile) obj;
		if (idLong != other.idLong)
			return false;
		return true;
	}


}
