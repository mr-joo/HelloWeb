package kr.co.oraclejava.controller;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.oraclejava.service.PlayerDao;

@Controller
public class HelloController<Player> {
	@Autowired
	private PlayerDao playerDao;

	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView model = new ModelAndView("/hello"); // views/hello.jsp
		model.addObject("msg", "�ȳ��ϼ���");
		model.addObject("date", new Date());
		return model;
	}

	// �� ���� view�� �̵��ϴ� ���� String���� ��ȯ
	@RequestMapping("/hello2")
	public String hello2() {
		return "hello2";
	}

	@RequestMapping("/hello3")
	public String hello3() {
		return "redirect:/hello2";
	}

	// favorite fruits list
	@RequestMapping("/fruits")
	public ModelAndView listFruits() {
		String[] fruits = { "��", "��", "���", "�ٳ���", "Ű��" };
		ModelAndView model = new ModelAndView("/fruits"); // views/hello.jsp
		model.addObject("list", fruits);
		return model;
	}

	// favorite fruits list(JSON)
	@RequestMapping("/api/fruits")
	@ResponseBody
	public String[] listJason() {
		String[] fruits = { "��", "��", "���", "�ٳ���", "Ű��" };
		return fruits;
	}
	
	//player list(JSON)
	@RequestMapping("/api/player")
	@ResponseBody
	public List<Player> listPlayerJson() {
		List<Player> list = (List<Player>)playerDao.getPlayerList();
		return list;
	}
}
