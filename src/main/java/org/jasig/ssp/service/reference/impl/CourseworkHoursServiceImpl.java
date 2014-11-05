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
package org.jasig.ssp.service.reference.impl;

import org.jasig.ssp.dao.reference.CourseworkHoursDao;
import org.jasig.ssp.model.reference.CourseworkHours;
import org.jasig.ssp.service.reference.CourseworkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional
public class CourseworkHoursServiceImpl extends
		AbstractReferenceService<CourseworkHours>
		implements CourseworkHoursService {

	@Autowired
	transient private CourseworkHoursDao dao;

	protected void setDao(final CourseworkHoursDao dao) {
		this.dao = dao;
	}

	@Override
	protected CourseworkHoursDao getDao() {
		return dao;
	}
}
