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
package org.jasig.ssp.service.reference;

import org.jasig.ssp.model.reference.JournalStep;
import org.jasig.ssp.model.reference.JournalStepDetail;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.ReferenceService;
import org.jasig.ssp.util.sort.PagingWrapper;
import org.jasig.ssp.util.sort.SortingAndPaging;

/**
 * JournalStepDetail service
 */
public interface JournalStepDetailService extends
		ReferenceService<JournalStepDetail> {

	PagingWrapper<JournalStepDetail> getAllForJournalStep(
			final JournalStep journalStep,
			final SortingAndPaging sAndP) throws ObjectNotFoundException;
}