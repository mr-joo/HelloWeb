package kr.co.oraclejava.controller;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.oraclejava.service.Player;
import kr.co.oraclejava.service.PlayerDao;

@Controller
@RequestMapping("kbo") // > /kbo
public class KboController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(KboController.class);

	@Autowired
	//@Qualifier("playerDaoMyBatis2")
	private PlayerDao playerDao;

	// url�� ""�̰ų� list�� �� method ȣ��
	// ȣ�� ����� GET ����ؼ� method�� ����
	@RequestMapping(value = { "", "list" }, method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("list");
		model.addAttribute("playerlist", playerDao.getPlayerList());
		return "/kbo/list";
	}

	// �����߰�
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("player", new Player());
		return "kbo/create";
	}

	// ceate
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(Player player, @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		logger.info(player.toString());
		String fileName = file.getOriginalFilename();
		if (!fileName.isEmpty() && !file.isEmpty()) {
			// file upload
			String uploadFileName = Instant.now().toString().replace(":", "") + fileName;
			file.transferTo(new File("c:/upload/" + uploadFileName));
			player.setFileName(uploadFileName);
		}

		playerDao.insertPlayer(player);
		return "redirect:/kbo/list";
	}

	// ���� �� ȭ��
	// spring2 ���� �̻���� {id} �� ��ü�� url�� �Ǳ� ������ ���ϰ� ��� ����
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") int playerid, Model model) {
		Player player = playerDao.getPlayer(playerid);
		if (player == null) {
			return "redirect:/kbo/list";
		}
		model.addAttribute(player);
		return "kbo/view";
	}

	// ���� ���� ȭ��
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int playerid, Model model) {
		Player player = playerDao.getPlayer(playerid);
		if (player == null) {
			return "redirect:/kbo/list";
		}
		model.addAttribute(player);
		return "kbo/update";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") int playerid, Player player, @RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		logger.info(player.toString());
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		if (!fileName.isEmpty() && !file.isEmpty()) {
			// file upload
			if (contentType.equals("image/jpg") || contentType.equals("image/jpeg")) {
				String uploadFileName = Instant.now().toString().replace(":", "") + fileName;
				file.transferTo(new File("c:/upload/" + uploadFileName));
				player.setFileName(uploadFileName);
			}
		}
		playerDao.updatePlayer(player);
		return "redirect:/kbo/list";
	}

	// ���� ���� ȭ��
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int playerid) {
		Player player = playerDao.getPlayer(playerid);
		if (player == null) {
			return "redirect:/kbo/list";
		}
		playerDao.deletePlayer(player);
		return "redirect:/kbo/list";
	}
}
