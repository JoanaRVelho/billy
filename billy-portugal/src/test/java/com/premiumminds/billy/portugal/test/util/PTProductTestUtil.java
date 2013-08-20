/**
 * Copyright (C) 2013 Premium Minds.
 *
 * This file is part of billy portugal (PT Pack).
 *
 * billy portugal (PT Pack) is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * billy portugal (PT Pack) is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with billy portugal (PT Pack). If not, see <http://www.gnu.org/licenses/>.
 */
package com.premiumminds.billy.portugal.test.util;

import com.google.inject.Injector;
import com.premiumminds.billy.core.services.UID;
import com.premiumminds.billy.core.services.entities.Product.ProductType;
import com.premiumminds.billy.portugal.persistence.entities.PTProductEntity;
import com.premiumminds.billy.portugal.persistence.entities.PTTaxEntity;
import com.premiumminds.billy.portugal.services.entities.PTProduct;
import com.premiumminds.billy.portugal.util.Taxes;

public class PTProductTestUtil {

	private static final String NUMBER_CODE = "123";
	private static final String UNIT_OF_MEASURE = "Kg";
	private static final String PRODUCT_CODE = "12345";
	private static final String DESCRIPTION = "DESCRIPTION";
	private static final String GROUP = "FOOD";
	private static final ProductType TYPE = ProductType.GOODS;

	private Injector injector;
	private Taxes taxes;
	private PTTaxEntity tax;

	public PTProductTestUtil(Injector injector) {
		this.injector = injector;
		this.taxes = new Taxes(injector);
		this.tax = (PTTaxEntity) this.taxes.continent().normal();
	}

	public PTProductEntity getProductEntity(String uid) {
		PTProductEntity product = (PTProductEntity) getProductBuilder().build();
		product.setUID(new UID(uid));

		return product;
	}

	public PTProductEntity getProductEntity() {
		return (PTProductEntity) getProductBuilder().build();
	}

	public PTProduct.Builder getProductBuilder(String productCode,
			String unitMesure, String numberCode, String group,
			String description, ProductType type) {
		PTProduct.Builder productBuilder = this.injector
				.getInstance(PTProduct.Builder.class);

		return productBuilder.addTaxUID(this.tax.getUID())
				.setNumberCode(numberCode).setUnitOfMeasure(unitMesure)
				.setProductCode(productCode).setDescription(description)
				.setType(type).setProductGroup(group);

	}

	public PTProduct.Builder getProductBuilder() {
		return getProductBuilder(PRODUCT_CODE, UNIT_OF_MEASURE, NUMBER_CODE,
				GROUP, DESCRIPTION, TYPE);
	}

	public PTProductEntity getProductEntity(String productCode,
			String unitMesure, String numberCode, String group, ProductType type) {
		return (PTProductEntity) getProductBuilder(productCode, unitMesure,
				numberCode, group, DESCRIPTION, type).build();

	}
}
