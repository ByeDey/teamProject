package movie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.model.MovieBean;
import movie.model.MovieDao;

@Controller
public class MovieUpdateController {

	private final String command = "update.mv";
	private final String getPage = "movieUpdateForm";
	private final String gotoPage = "redirect:/list.mv";

	@Autowired
	MovieDao mdao;

	// movieList.jsp
	@RequestMapping(value = command,method = RequestMethod.GET)
	public ModelAndView doAction(HttpServletRequest request,
			@RequestParam("num") int num,
			@RequestParam("pageNumber") int pageNumber) {

		MovieBean mb = mdao.getOneMovie(num);

		System.out.println("update mb : " + mb.getNum());
		System.out.println("update.mv get pageNumber : " + pageNumber);

		//request.setAttribute("mb", mb);
		ModelAndView mav = new ModelAndView();
		mav.addObject("movie", mb);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage); // 수정폼

		return mav;
	}
	
	
	// updateForm.jsp submit 
	// updateForm.jsp submit 추가하기 
	// updateForm.jsp submit 한글 처리 테스트 
	// SJH 팀원이 수정함!
	// SJH 팀원이 수정함! 2차 테스트
	// SJH 팀원이 수정함! 3차 테스트  
	// 마스터가 수정함!
	@RequestMapping(value = command,method = RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("movie") @Valid MovieBean movie,BindingResult result,
								@RequestParam("pageNumber") int pageNumber) {
		
		System.out.println("MUC post pageNumber : " + pageNumber);
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.addObject("pageNumber", pageNumber); 
			mav.setViewName(getPage); // 수정form
			return mav;
		}
		
		int cnt = mdao.updateMovie(movie);
		mav.addObject("pageNumber", pageNumber); 
		mav.setViewName(gotoPage); // "redirect:/list.mv?pageNumber="+3;
		
		return mav;
	}
}
