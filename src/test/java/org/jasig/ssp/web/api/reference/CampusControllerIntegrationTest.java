package org.jasig.ssp.web.api.reference;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.UUID;

import org.jasig.ssp.model.ObjectStatus;
import org.jasig.ssp.model.Person;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.impl.SecurityServiceInTestEnvironment;
import org.jasig.ssp.transferobject.reference.CampusTO;
import org.jasig.ssp.web.api.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link CampusController} tests
 * 
 * @author jon.adams
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../../ControllerIntegrationTests-context.xml")
@TransactionConfiguration
@Transactional
public class CampusControllerIntegrationTest {

	@Autowired
	private transient CampusController controller;

	@Autowired
	private transient SecurityServiceInTestEnvironment securityService;

	/**
	 * Setup the security service with the administrator.
	 */
	@Before
	public void setUp() {
		securityService.setCurrent(new Person(Person.SYSTEM_ADMINISTRATOR_ID));
	}

	/**
	 * Test that the {@link CampusController#get(UUID)} action returns the
	 * correct validation errors when an invalid ID is sent.
	 * 
	 * @throws ObjectNotFoundException
	 *             If lookup data can not be found.
	 * @throws ValidationException
	 *             If there are any validation errors.
	 */
	@Test(expected = ObjectNotFoundException.class)
	public void testControllerGetOfInvalidId() throws ObjectNotFoundException,
			ValidationException {
		assertNotNull(
				"Controller under test was not initialized by the container correctly.",
				controller);

		final CampusTO obj = controller.get(UUID.randomUUID());

		assertNull(
				"Returned CampusTO from the controller should have been null.",
				obj);
	}

	/**
	 * Test the
	 * {@link CampusController#getAll(ObjectStatus, Integer, Integer, String, String)}
	 * action.
	 */
	@Test
	public void testControllerAll() {
		final Collection<CampusTO> list = controller.getAll(
				ObjectStatus.ACTIVE, null, null, null, null).getRows();

		assertNotNull("List should not have been null.", list);
	}

	/**
	 * Test that the getAll action rejects a filter of
	 * {@link ObjectStatus#DELETED}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testControllerGetAllRejectsDeletedFilter() {
		controller.getAll(ObjectStatus.DELETED, null, null, null, null);
	}
}