/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.ssp.web.api.reference;

import org.jasig.ssp.factory.TOFactory;
import org.jasig.ssp.factory.reference.DisabilityAgencyTOFactory;
import org.jasig.ssp.model.reference.DisabilityAgency;
import org.jasig.ssp.service.AuditableCrudService;
import org.jasig.ssp.service.reference.DisabilityAgencyService;
import org.jasig.ssp.transferobject.reference.DisabilityAgencyTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/1/reference/disabilityAgency")
public class DisabilityAgencyController
		extends
		AbstractAuditableReferenceController<DisabilityAgency, DisabilityAgencyTO> {

	@Autowired
	protected transient DisabilityAgencyService service;

	@Override
	protected AuditableCrudService<DisabilityAgency> getService() {
		return service;
	}

	@Autowired
	protected transient DisabilityAgencyTOFactory factory;

	@Override
	protected TOFactory<DisabilityAgencyTO, DisabilityAgency> getFactory() {
		return factory;
	}

	protected DisabilityAgencyController() {
		super(DisabilityAgency.class, DisabilityAgencyTO.class);
	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DisabilityAgencyController.class);

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}
}
