/**
 * Copyright (C) 2013 Premium Minds.
 * 
 * This file is part of billy core.
 * 
 * billy core is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * billy core is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with billy core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.premiumminds.billy.core.test.services.builders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.premiumminds.billy.core.persistence.dao.DAOApplication;
import com.premiumminds.billy.core.persistence.entities.ContactEntity;
import com.premiumminds.billy.core.services.entities.Application;
import com.premiumminds.billy.core.services.entities.Contact;
import com.premiumminds.billy.core.test.AbstractTest;
import com.premiumminds.billy.core.test.fixtures.MockApplicationEntity;

public class TestApplicationBuilder extends AbstractTest {

	private static final String APPLICATION_YML = "src/test/resources/Application.yml";

	@Test
	public void doTest() {
		MockApplicationEntity mockApplication = createMockEntity(
				MockApplicationEntity.class, APPLICATION_YML);

		Mockito.when(getInstance(DAOApplication.class).getEntityInstance())
				.thenReturn(new MockApplicationEntity());

		Application.Builder builder = getInstance(Application.Builder.class);

		ArrayList<ContactEntity> contacts = (ArrayList<ContactEntity>) mockApplication
				.getContacts();

		Contact.Builder mockContactBuilder1 = this
				.getMock(Contact.Builder.class);
		Mockito.when(mockContactBuilder1.build()).thenReturn(contacts.get(0));

		Contact.Builder mockContactBuilder2 = this
				.getMock(Contact.Builder.class);
		Mockito.when(mockContactBuilder2.build()).thenReturn(contacts.get(1));

		Contact.Builder mockMainContactBuilder = this
				.getMock(Contact.Builder.class);
		Mockito.when(mockMainContactBuilder.build()).thenReturn(
				mockApplication.getMainContact());

		builder.addContact(mockContactBuilder1)
				.addContact(mockContactBuilder2)
				.addContact(mockMainContactBuilder)
				.setDeveloperCompanyName(
						mockApplication.getDeveloperCompanyName())
				.setDeveloperCompanyTaxIdentifier(
						mockApplication.getDeveloperCompanyTaxIdentifier())
				.setMainContact(mockMainContactBuilder)
				.setName(mockApplication.getName())
				.setVersion(mockApplication.getVersion())
				.setWebsiteAddress(mockApplication.getWebsiteAddress());

		Application application = builder.build();

		assertTrue(application != null);
		assertEquals(mockApplication.getName(), application.getName());
		assertEquals(mockApplication.getVersion(), application.getVersion());
		assertEquals(mockApplication.getDeveloperCompanyName(),
				application.getDeveloperCompanyName());
		assertEquals(mockApplication.getDeveloperCompanyTaxIdentifier(),
				application.getDeveloperCompanyTaxIdentifier());
		assertEquals(mockApplication.getWebsiteAddress(),
				application.getWebsiteAddress());

		assertTrue(application.getMainContact() != null);
		assertEquals(application.getMainContact().getName(), mockApplication
				.getMainContact().getName());

		assertTrue(application.getContacts() != null);
		assertEquals(application.getContacts().size(), mockApplication
				.getContacts().size() + 1);

		for (int i = 0; i < application.getContacts().size() - 1; i++) {
			List<Contact> appContacts = (List<Contact>) application
					.getContacts();
			assertEquals(appContacts.get(i).getName(), mockApplication
					.getContacts().get(i).getName());
		}

	}

}
