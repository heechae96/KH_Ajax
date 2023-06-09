package com.kh.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.ajax.Alert;
import com.kh.ajax.domain.Reply;
import com.kh.common.file.FileUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bService;
	
	@Autowired				// 의존성 주입
//	@Qualifier("fileUtil")	// 빈으로 등록
	private FileUtil fileUtil;

	@GetMapping("/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("board/register");
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView register(ModelAndView mv, @ModelAttribute Board board,
			@RequestParam(name = "uploadFile", required = false) MultipartFile multipartFile, HttpServletRequest req) {
		Map<String, String> fileInfo = null;
		try {
//			fileInfo = fileUtil.saveFile(multipartFile, req);
//			board.setBoardFilename(fileInfo.get("original"));
//			board.setBoardFileRename(fileInfo.get("rename"));
//			board.setBoardFilepath(fileInfo.get("renameFilePath"));
			int result = bService.insertBoard(board);
			if (result > 0) {
				mv.setViewName("redirect:/board/list");
			} else {
				Alert alert = new Alert("/board/register", "게시글 등록 실패");
				mv.addObject("alert", alert);
				mv.setViewName("common/alert");
			}
		} catch (Exception e) {
			e.printStackTrace(); // 콘솔창에 에러 출력
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
		}
		return mv;
	}

	@GetMapping("/list")
	public ModelAndView list(ModelAndView mv) {
		try {
			List<Board> bList = bService.selectAll();
			if (bList.isEmpty()) {
				Alert alert = new Alert("/board/register", "게시글이 존재하지 않습니다");
				mv.addObject("alert", alert);
				mv.setViewName("common/alert");
			} else {
				mv.addObject("bList", bList);
				mv.setViewName("board/list");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
		}
		return mv;
	}

	@GetMapping("/detail/{boardNo}")
	public ModelAndView detail(ModelAndView mv, @PathVariable int boardNo) {
		try {
			Board board = bService.selectOneByNo(boardNo);
			if (board != null) {
				mv.addObject("board", board);
				mv.setViewName("board/detail");
			} else {
				Alert alert = new Alert("/board/list", "해당 게시글이 존재하지 않습니다");
				mv.addObject("alert", alert);
				mv.setViewName("common/alert");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
		}
		return mv;
	}

	@ResponseBody
	@PostMapping("/reply/register")
	public String doReplyInsert(@ModelAttribute Reply reply) {
		try {
			int result = bService.insertReply(reply);
			if (result > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@GetMapping(value = "/reply/list", produces = "application/json; charset=UTF-8")
	public String viewReplyList(int boardNo) {
		List<Reply> rList = bService.selectAllReply(boardNo);
		return new Gson().toJson(rList);
	}

	@ResponseBody
	@PostMapping(value = "/reply/modify", produces = "application/json; charset=UTF-8")
	public String doReplyUpdate(@ModelAttribute Reply reply) {
		try {
			int result = bService.updateReply(reply);
			if (result > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@GetMapping(value = "/reply/delete", produces = "application/json; charset=UTF-8")
	public String doReplyDelete(int replyNo) {
		try {
			int result = bService.deleteReply(replyNo);
			if (result > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
