package com.kh.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.ajax.domain.Member;

@Controller
public class AjaxController {

	// Model과 view가 분리된 상태
	@GetMapping("/ajax/sample")
	public String showAjaxWrapUp(Model model) {
		return "ajax/wrapup";
	}

	// Model과 view를 합친 상태
	@GetMapping("/ajax/wrapup")
	public ModelAndView showAjaxWrapUp(ModelAndView mv) {
		mv.setViewName("ajax/wrapup");
		return mv;
	}

	// ajax를 사용하기 위해 필요한 애너테이션 @ResponseBody
	@ResponseBody
	@GetMapping(value = "/ajax/first")
	public void ajaxFirst(@RequestParam("msg") String msg) {
		System.out.println("========================================================");
		System.out.println("전송 받은 데이터[first] : " + msg);
		System.out.println("========================================================");
	}

	@ResponseBody
	@GetMapping(value = "/ajax/second")
	public void ajaxSecond(@RequestParam("msg") String msg) {
		System.out.println("========================================================");
		System.out.println("전송 받은 데이터[second] : " + msg);
		System.out.println("========================================================");
	}

	@ResponseBody
	@GetMapping(value = "/ajax/third", produces = "text/plain; charset=UTF-8")
	public String ajaxThird() {
		return "FROM 서버";
	}

	@ResponseBody
	@GetMapping(value = "/ajax/fourth")
	public String ajaxFourth(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
		int result = num1 + num2;
		System.out.println("========================================================");
		System.out.println("전송 받은 데이터[num1] : " + num1);
		System.out.println("전송 받은 데이터[num2] : " + num2);
		System.out.println("========================================================");
		return String.valueOf(result);
	}

	@ResponseBody
	@GetMapping(value = "/ajax/fifth", produces = "application/json; charset=UTF-8")
	public String ajaxFifth(@RequestParam("memberId") String memberId) {
		List<Member> mList = new ArrayList<Member>();
		mList.add(new Member("khuser01", "pass01"));
		mList.add(new Member("khuser02", "pass02"));
		mList.add(new Member("khuser03", "pass03"));
		mList.add(new Member("khuser04", "pass04"));
		mList.add(new Member("khuser05", "pass05"));

		Member member = null;
		for (Member mOne : mList) {
			if (mOne.getMemberId().equals(memberId)) {
				member = mOne;
				break;
			}
		}

		// json 객체 생성 -> { } 생성 완료
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("memberId", member.getMemberId());
		jsonObj.put("memberPw", member.getMemberPw());

		// 현재 상태
		// { "memberId" : "khuser01", "memberPw" : "pass01" }

		// return jsonObj.toJSONString();
		// produces = "application/json; charset=UTF-8"
		// 위에서 json을 이미 적어줘서 toString()으로 대체
		return jsonObj.toString();

	}

	@ResponseBody
	@GetMapping(value = "/ajax/sixth", produces = "application/json; charset=UTF-8")
	public String ajaxSixth(@RequestParam("userId") String userId) {
		List<Member> mList = new ArrayList<Member>();
		mList.add(new Member("khuser01", "pass01"));
		mList.add(new Member("khuser02", "pass02"));
		mList.add(new Member("khuser03", "pass03"));
		mList.add(new Member("khuser04", "pass04"));
		mList.add(new Member("khuser05", "pass05"));

		boolean chkFlag = false;
		JSONArray jsonArr = new JSONArray(); // [ ]

		for (Member mOne : mList) {
			if (mOne.getMemberId().equals(userId)) {
				// 존재 하면 해당 유저를 보냄
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("memberId", mOne.getMemberId());
				jsonObj.put("memberPw", mOne.getMemberPw());
				jsonArr.add(jsonObj); // [ { "memberId" : "khuser01", "memberPw" : "pass01" } ]
				chkFlag = true;
				break;
			}
		}

		// 존재 하지 않으면 전체리스트 보냄
		if (!chkFlag) {
			for (Member mOne : mList) {
				JSONObject jsonObj2 = new JSONObject();
				jsonObj2.put("memberId", mOne.getMemberId());
				jsonObj2.put("memberPw", mOne.getMemberPw());
				jsonArr.add(jsonObj2);
				// [ { "memberId" : "khuser01", "memberPw" : "pass01" },
				// { "memberId" : "khuser02", "memberPw" : "pass02" },
				// ...
				// ]
			}
		}

		return jsonArr.toString();

	}

	@ResponseBody
	@GetMapping(value = "/ajax/seventh", produces = "application/json; charset=UTF-8")
	public String ajaxSeventh() {
		List<Member> mList = new ArrayList<Member>();
		mList.add(new Member("khuser01", "pass01"));
		mList.add(new Member("khuser02", "pass02"));
		mList.add(new Member("khuser03", "pass03"));
		mList.add(new Member("khuser04", "pass04"));
		mList.add(new Member("khuser05", "pass05"));

		// 수동 -> 자동
		return new Gson().toJson(mList);

		// 수동
//		JSONArray jsonArr = new JSONArray(); // [ ]
//		for (Member mOne : mList) {
//			JSONObject jsonObj2 = new JSONObject();
//			jsonObj2.put("memberId", mOne.getMemberId());
//			jsonObj2.put("memberPw", mOne.getMemberPw());
//			jsonArr.add(jsonObj2);
//			// [ { "memberId" : "khuser01", "memberPw" : "pass01" },
//			// { "memberId" : "khuser02", "memberPw" : "pass02" },
//			// ...
//			// ]
//		}
//		return jsonArr.toString();

	}

}