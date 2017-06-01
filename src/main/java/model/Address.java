package model;


import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = -4301007029555241358L;
    private String fax;
    private String mobilePhone;
    private String phone;
    private String zipCode;
    private String country;
    private String province;
    private String city;
    private String street;
    private String email;



    public Address(String fax, String mobilePhone, String phone, String zipCode, String country, String province,
			String city, String street, String email) {
		super();
		this.fax = fax;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.zipCode = zipCode;
		this.country = country;
		this.province = province;
		this.city = city;
		this.street = street;
		this.email = email;
	}

	public Object getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
