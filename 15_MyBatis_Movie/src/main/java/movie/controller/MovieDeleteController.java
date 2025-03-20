package movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import movie.model.MovieDao;
   
@Controller
public class MovieDeleteController {
	private final String command = "delete.mv"; 
	private final String gotoPage = "redirect:/list.mv";
	  
	@Autowired  
	MovieDao mdao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doActionGet(@RequestParam(value = "num",required = true) int num) {
		System.out.println("MDC num : " + num);
		
		int cnt = mdao.deleteMovie(num);
		return gotoPage;
		
	}
}
