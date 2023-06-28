package com.example.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller

public class GameController {
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String index() {
		session.invalidate();
		// 答えを作って Session に格納
		Random rnd = new Random();
		int answer = rnd.nextInt(100) + 1;
		session.setAttribute("answer", answer);
		System.out.println("answer=" + answer);
		return "game";
	}

	@PostMapping("/challenge")
	public ModelAndView challenge(@RequestParam(value = "number", required = false) String number, ModelAndView mv) {
		// セッションから答えを取得
		int answer = (Integer) session.getAttribute("answer");
		// ユーザーの回答履歴を取得
		@SuppressWarnings("unchecked")
		List<History> histories = (List<History>) session.getAttribute("histories");
		@SuppressWarnings("unchecked")
		List<History> histories2 = (List<History>) session.getAttribute("histories2");
		if (histories == null) {
			histories = new ArrayList<>();
			session.setAttribute("histories", histories);
		}
		
		if (histories2 == null) {
			histories2 = new ArrayList<>();
			session.setAttribute("histories2", histories2);
		}
		
		int num = 0;
		try {
			num = Integer.parseInt(number);
			if (answer < num) {
				histories.add(new History(histories.size() + 1, num, "もっと小さいです"));
			} else if (answer == num) {
				histories.add(new History(histories.size() + 1, num, "正解です！"));
			} else {
				histories.add(new History(histories.size() + 1, num, "もっと大きいです"));
			}

		} catch (NumberFormatException num_error) {
			histories2.add(new History(histories2.size() + 1, num, "もっと小さいです"));

		}

		Map<String,List<History>> map = new HashMap<>();
		map.put("1",histories);
		map.put("2",histories2);
		return gameView(map);
	}
	
	
	/*
	 * 表示作成用メソッド
	 */
	public static ModelAndView gameView(Map<String,List<History>> map) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("histories", map.get("1"));
		mv.addObject("histories2", map.get("2"));
		mv.setViewName("game");
		return mv;
	}
	
}
