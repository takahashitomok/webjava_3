package jp.co.systena.tigerscave.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.model.ListForm;
import jp.co.systena.tigerscave.model.Party;

@SpringBootApplication
@RestController
public class CommandViewController {

	@Controller
	public class CommandController {

		@Autowired
		  HttpSession session;

		  @RequestMapping(value = "/command", method = RequestMethod.GET)
		  public ModelAndView show(ModelAndView mav) {

		    ListForm characterDispData = new ListForm();
		    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);

		    characterDispData = party.setDispData(characterDispData);

		    mav.addObject("character", characterDispData);

		    mav.setViewName("Command");

		    return mav;
		  }

		  @RequestMapping(value = "/command", params = "next", method = RequestMethod.POST)
		  public ModelAndView makedCharacter(ModelAndView mav, ListForm characterInput) {
		    // パーティーに追加＋セッション保存
		    addMemberAndSaveSession(characterInput.getName(), characterInput.getJob());

		    return new ModelAndView("redirect:/command"); // リダイレクト
		  }

		  @RequestMapping(value = "/command", params = "repeat", method = RequestMethod.POST)
		  public ModelAndView reMakedCharacter(ModelAndView mav, ListForm characterInput) {
		    // パーティーに追加＋セッション保存
		    addMemberAndSaveSession(characterInput.getName(), characterInput.getJob());

		    return new ModelAndView("redirect:/charactermake"); // リダイレクト
		  }

		  private void addMemberAndSaveSession(String name, String job) {
		    Party party = (Party) session.getAttribute(Party.PARTY_SESSION_KEY);
		    if (party == null) {
		      party = new Party();
		    }
		    // パーティーに追加
		    party.addMember(name, job);

		    session.setAttribute(Party.PARTY_SESSION_KEY, party);
		  }

		  @RequestMapping(value = "/command", params = "redirectCommand", method = RequestMethod.POST)
		  public ModelAndView redirectCommand(ModelAndView mav, ListForm characterInput) {

		    return new ModelAndView("redirect:/command"); // リダイレクト
		  }
	}
}