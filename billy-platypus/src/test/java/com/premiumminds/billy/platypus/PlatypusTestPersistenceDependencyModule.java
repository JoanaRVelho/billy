/**
 * Copyright (C) 2013 Premium Minds.
 *
 * This file is part of billy.
 *
 * billy is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * billy is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with billy. If not, see <http://www.gnu.org/licenses/>.
 */
package com.premiumminds.billy.platypus;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class PlatypusTestPersistenceDependencyModule extends AbstractModule {

	@Override
	protected void configure() {
		JpaPersistModule persistModule = new JpaPersistModule("BillyPlatypusTestPersistenceUnit");
		install(persistModule);
	}

	public static class Initializer {
		
		@Inject public Initializer(PersistService persistService) {
			persistService.start();
		}
	}
	
}
