package utilities;

import javax.servlet.http.HttpServletRequest;

public class SessionControl {
	
	public static String sessionContoller(HttpServletRequest reqister, String page ) {
		
boolean statu = reqister.getSession().getAttribute("userid") != null;
		
		if(statu) {
			
			return page;
			
		}
			
			return "redirect:/";
		
	}

}
