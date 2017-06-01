package model;


import java.io.Serializable;

public class PrivateAddress extends Address implements Serializable {

    public PrivateAddress(String fax, String mobilePhone, String phone, String zipCode, String country, String province,
			String city, String street, String email) {
		super(fax, mobilePhone, phone, zipCode, country, province, city, street, email);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -4301007029555241358L;


}
