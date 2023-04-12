package com.kh.photo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.ajax.Alert;
import com.kh.common.file.FileUtil;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	private PhotoService pService;

	@Autowired
	@Qualifier("fileUtil")
	private FileUtil fileUtil;

	@GetMapping("/insert")
	public ModelAndView viewPhotoInsert(ModelAndView mv) {
		mv.setViewName("photo/insert");
		return mv;
	}

	@PostMapping("/insert")
	public ModelAndView viewPhotoInsert(ModelAndView mv,
			@RequestParam(name = "uploadFile", required = false) MultipartFile mp, HttpServletRequest req,
			@ModelAttribute Photo photo) {
		Map<String, String> fileInfo = null;
		try {
//			fileInfo = fileUtil.saveFile(mp, req);
//			photo.setPhotoFilename(fileInfo.get("original"));
//			photo.setPhotoFileRename(fileInfo.get("rename"));
//			photo.setPhotoFilepath(fileInfo.get("renameFilePath"));
			int result = pService.insertPhoto(photo);
			if (result > 0) {
				Alert alert = new Alert("/photo/list", "사진 등록 성공");
				mv.addObject("alert", alert);
				mv.setViewName("common/alert");
			} else {
				Alert alert = new Alert("/photo/list", "사진 등록 실패");
				mv.addObject("alert", alert);
				mv.setViewName("common/alert");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("common/error");
		}
//		mv.setViewName("redirect:/photo/list");
		return mv;
	}

	@GetMapping("/list")
	public ModelAndView viewPhotoList(ModelAndView mv) {
		mv.setViewName("photo/list");
		return mv;
	}

	@ResponseBody
	@PostMapping(value = "/more", produces = "application/json; charset=utf-8")
	public String viewPhotoList(int start) {
		List<Photo> pList = pService.morePhoto(start);
		return new Gson().toJson(pList);
	}

}
