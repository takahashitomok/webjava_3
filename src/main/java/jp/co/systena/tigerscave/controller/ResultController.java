package jp.co.systena.tigerscave.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.model.Enemy;
import jp.co.systena.tigerscave.model.Job;
import jp.co.systena.tigerscave.model.Party;
import jp.co.systena.tigerscave.model.ResultForm;

@RestController
public class ResultController {
	@Autowired
	HttpSession session;

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public ModelAndView show(ModelAndView mav) {
		Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);
		List<Object> partyMember = party.getPartyMember();
		ResultForm resultForm = new ResultForm();

		Enemy enemy = (Enemy) session.getAttribute(Enemy.ENEMY_KEY);
		if (enemy == null) {
			enemy = new Enemy();
		}
		for (Object member : partyMember) {
			Job Job = (Job) member;
			if (Job.getAction().equals(jp.co.systena.tigerscave.model.Job.FIGHT)) {
				// たかかう選択
				if (Math.random() > 0.8) {
					resultForm.addResult(Job.strongFight());
					enemy.strongDamage();
				} else {
					resultForm.addResult(Job.fight());
					enemy.damage();
				}
			} else {
				// かいふく選択
				resultForm.addResult(Job.recovery());
			}
		}
		mav.addObject("result", resultForm);
		mav.addObject("enemy", enemy);

		session.setAttribute(Enemy.ENEMY_KEY, enemy);
		party.resetDispNumber();

		mav.setViewName("Result");

		// session.removeAttribute(Party.PARTY_SESSION_KEY);

		return mav;
	}

	@RequestMapping(value = "/result", params = "fight", method = RequestMethod.POST)
	public ModelAndView commandFight(ModelAndView mav) {
		Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);

		List<Object> partyMember = party.getPartyMember();
		int dispNumber = party.getDispNumber();
		Job Job = (Job) partyMember.get(dispNumber);
		Job.setAction(jp.co.systena.tigerscave.model.Job.FIGHT);
		partyMember.set(dispNumber, Job);

		session.setAttribute(Party.PARTY_SESSION_KEY, party);

		// パーティー人数分表示されたら結果画面へ遷移
		if (party.getNumberOfPeople() > dispNumber + 1) {
			party.addDispNumber();
			return new ModelAndView("redirect:/command");
		}
		return new ModelAndView("redirect:/result"); // リダイレクト
	}

	@RequestMapping(value = "/result", params = "recovery", method = RequestMethod.POST)
	public ModelAndView commandRecovery(ModelAndView mav) {
		Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);

		List<Object> partyMember = party.getPartyMember();
		int dispNumber = party.getDispNumber();
		Job Job = (Job) partyMember.get(dispNumber);
		Job.setAction(jp.co.systena.tigerscave.model.Job.RECOVERY);
		partyMember.set(dispNumber, Job);

		session.setAttribute(Party.PARTY_SESSION_KEY, party);

		// パーティー人数分表示されたら結果画面へ遷移
		if (party.getNumberOfPeople() > dispNumber + 1) {
			party.addDispNumber();
			return new ModelAndView("redirect:/command");
		}
		return new ModelAndView("redirect:/result"); // リダイレクト
	}
}