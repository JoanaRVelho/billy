package com.premiumminds.billy.core.test.fixtures;

import java.util.ArrayList;
import java.util.List;

import com.premiumminds.billy.core.persistence.entities.ApplicationEntity;
import com.premiumminds.billy.core.persistence.entities.ContactEntity;
import com.premiumminds.billy.core.services.entities.Contact;

public class MockApplicationEntity extends MockBaseEntity implements
		ApplicationEntity {

	private static final long serialVersionUID = 1L;

	public String name;
	public String version;
	public String developerCompanyName;
	public String developerCompanyTaxIdentifier;
	public String websiteAddress;
	public ContactEntity mainContact;
	public List<ContactEntity> contacts;

	public MockApplicationEntity() {
		this.contacts = new ArrayList<ContactEntity>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	@Override
	public String getDeveloperCompanyName() {
		return this.developerCompanyName;
	}

	@Override
	public String getDeveloperCompanyTaxIdentifier() {
		return this.developerCompanyTaxIdentifier;
	}

	@Override
	public String getWebsiteAddress() {
		return this.websiteAddress;
	}

	@Override
	public <T extends Contact> Contact getMainContact() {
		return this.mainContact;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;

	}

	@Override
	public void setDeveloperCompanyName(String name) {
		this.developerCompanyName = name;
	}

	@Override
	public void setDeveloperCompanyTaxIdentifier(String id) {
		this.developerCompanyTaxIdentifier = id;
	}

	@Override
	public List<ContactEntity> getContacts() {
		return this.contacts;
	}

	@Override
	public <T extends ContactEntity> void setMainContact(T contact) {
		this.mainContact = contact;
	}

	@Override
	public void setWebsiteAddress(String website) {
		this.websiteAddress = website;
	}

}
