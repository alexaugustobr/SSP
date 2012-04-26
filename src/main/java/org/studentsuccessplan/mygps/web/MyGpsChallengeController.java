package org.studentsuccessplan.mygps.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.studentsuccessplan.ssp.service.reference.ChallengeReferralService;
import org.studentsuccessplan.ssp.service.reference.ChallengeService;
import org.studentsuccessplan.ssp.transferobject.reference.ChallengeReferralTO;
import org.studentsuccessplan.ssp.transferobject.reference.ChallengeTO;

@Controller
@RequestMapping("/1/mygps/challenge")
public class MyGpsChallengeController extends AbstractMyGpsController {

	@Autowired
	private transient ChallengeService challengeService;

	@Autowired
	private transient ChallengeReferralService challengeReferralService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyGpsChallengeController.class);

	/**
	 * Empty constructor
	 */
	public MyGpsChallengeController() {
		super();
	}

	// Needed for tests, will be removed in the future.
	public MyGpsChallengeController(final ChallengeService challengeService) {
		super();
		this.challengeService = challengeService;
	}

	/**
	 * Retrieve all applicable, visible Challenges for the specified query, that
	 * are not already assigned as Tasks for the current user.
	 * <p>
	 * Also filters out inactive Challenges, and those that are not marked to
	 * show in the SelfHelpSearch.
	 * 
	 * @param query
	 *            Text string to compare with a SQL LIKE clause on the
	 *            SelfHelpGuide Question, Description, and Tags fields
	 * @return All Challenges that match the specified criteria
	 * @throws Exception
	 *             If there were any unexpected exceptions thrown.
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody
	List<ChallengeTO> search(@RequestParam("query") final String query)
			throws Exception {
		try {
			final List<ChallengeTO> challenges = ChallengeTO
					.listToTOList(challengeService
							.challengeSearch(query));

			// TODO: (performance) Challenge search service does the
			// byChallengeIdNotOnActiveTaskList lookup already but doesn't
			// return the TOs so it has to be done again here. Or better yet,
			// done as a database set operation instead.
			for (ChallengeTO challenge : challenges) {
				challenge
						.setChallengeChallengeReferrals(ChallengeReferralTO
								.listToTOSet(challengeReferralService
										.byChallengeIdNotOnActiveTaskList(
												challenge.asModel(),
												securityService.currentUser()
														.getPerson(),
												securityService.getSessionId())));
			}

			return challenges;
		} catch (Exception e) {
			LOGGER.error("ERROR : search() : {}", e.getMessage(), e);
			throw e;
		}
	}
}
