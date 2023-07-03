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

import com.example.output.dao.ErrorHistory;
import com.example.output.dao.History;

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
		return "/game/game";
	}

	@PostMapping("/challenge")
	public ModelAndView challenge(@RequestParam(value = "number", required = false) String number, ModelAndView mv) {
		// セッションから答えを取得
		int answer = (Integer) session.getAttribute("answer");
		// ユーザーの回答履歴を取得
		@SuppressWarnings("unchecked")
		List<History> histories = (List<History>) session.getAttribute("histories");
		@SuppressWarnings("unchecked")
		List<ErrorHistory> errorHistories = (List<ErrorHistory>) session.getAttribute("errorHistories");
		if (histories == null) {
			histories = new ArrayList<>();
			session.setAttribute("histories", histories);
		}
		
		if (errorHistories == null) {
			errorHistories = new ArrayList<>();
			session.setAttribute("errorHistories", errorHistories);
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
			errorHistories.add(new ErrorHistory(errorHistories.size() + 1, ""+num_error, "エラーです"));

		}

		Map<String, List<History>> dao1Map = new HashMap<>();
		dao1Map.put("1", histories);

		Map<String, List<ErrorHistory>> dao2Map = new HashMap<>();
		dao2Map.put("1", errorHistories);

		return gameView(dao1Map, dao2Map);
	}
	
	
	/*
	 * 表示作成用メソッド
	 */
	public ModelAndView gameView(Map<String, List<History>> dao1Map, Map<String, List<ErrorHistory>> dao2Map) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("histories", dao1Map.get("1"));
		mv.addObject("errorHistories", dao2Map.get("1"));
		mv.setViewName("/game/game");
		return mv;
	}
	
}
