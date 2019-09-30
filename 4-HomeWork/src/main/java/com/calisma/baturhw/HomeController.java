package com.calisma.baturhw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utilities.DB;


@Controller
public class HomeController {
	
	String dbEmail = "";
	String dbPassword ="";
	int dbid = 0;
	Boolean statu = false;
	
	DB db = new DB();
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String home() {
		
		
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String login(Model model,@RequestParam String uemail,
								    @RequestParam String upassword,HttpServletRequest request) {
	
		try {
			
			String selectQuery = "SELECT * FROM tusers";
			PreparedStatement pre = db.connect(selectQuery);
			ResultSet rs = pre.executeQuery();
			
			while (rs.next()) {
				
				dbid = rs.getInt("uid");
				dbEmail = rs.getString("umail");
				dbPassword = rs.getString("upassword");
				
				if(dbEmail.equals(uemail) && dbPassword.equals(upassword)) {
					
					request.getSession().setAttribute("userid",dbid);
					System.out.println("user id ="+dbid);
					
					return "redirect:/cartInsertPage";
					
				}
				
			}
			
			

			
		} catch (Exception e) {
			System.err.println("HomeController Sql Err : "+e);
		}
		
		model.addAttribute("loginFail", "Email or Password is wrong!");
		
		return "home";
	
	}
	
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(HttpServletRequest req) {
		
		req.getSession().removeAttribute("userid");
		req.getSession().invalidate();
		
		return "redirect:/";
		
	}
	
}
