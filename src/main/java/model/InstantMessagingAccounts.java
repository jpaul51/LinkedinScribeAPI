package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InstantMessagingAccounts {

 @Id
 @GeneratedValue
	long id;
    private String skype;
    private String googletalk;


    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getGoogletalk() {
        return googletalk;
    }

    public void setGoogletalk(String googletalk) {
        this.googletalk = googletalk;
    }

}
