package movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import movie.model.MovieDao;

@Controller
public class MovieCheckController {
	
	private final String command = "title_check_proc.mv";
	
	@Autowired
	MovieDao mdao;
	
	// movieInsertForm.jsp���� �ߺ�üũ Ŭ��
	@RequestMapping(command)
	@ResponseBody // 
	public String doAction(@RequestParam(value="inputtitle", required = true) String inputtitle) {
		
		System.out.println("inputtitle : " + inputtitle); // ��Ʈ��
		
		int cnt = mdao.findTitle(inputtitle);
		System.out.println("MCC cnt : " + cnt);
		
		if(cnt == 0) {//��밡��
			return "YES";
			
		}else {//��� �Ұ���
			return "NO";
		}
	}
}







