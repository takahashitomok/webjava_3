package jp.co.systena.tigerscave.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.model.ListForm;
import jp.co.systena.tigerscave.model.Party;


@SpringBootApplication
@RestController
public class Controller {

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/charactermake", method = RequestMethod.GET)
	public ModelAndView show(ModelAndView mav) {
		ListForm ListForm = new ListForm();
		Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);
		if (party == null) {
			party = new Party();
		}

		party.checkDisplayContinued(ListForm);

		mav.addObject("character", ListForm);


		mav.setViewName("CharacterMake");

		return mav;
	}

}