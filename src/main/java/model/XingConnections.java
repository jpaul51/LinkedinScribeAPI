package model;

/**
 * A model class containing a list of a user's connections on Xing.
 *
 * @author Johannes Buehler
 */

public class XingConnections extends XingObject {

    private Contacts contacts;


    public XingConnections() {
    }


    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }
}
