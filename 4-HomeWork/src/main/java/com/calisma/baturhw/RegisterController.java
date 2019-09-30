package com.calisma.baturhw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import utilities.DB;

@Controller
public class RegisterController {
	
	DB db = new DB();
	
	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String register(Model model) {
		
		
		return "registerPage";
	}
	
	@RequestMapping(value = "/registerPage", method = RequestMethod.POST)
	public String insertUser (Model model,  @RequestParam String umail,
											@RequestParam String uname,					
											@RequestParam String usurname,
											@RequestParam String uphone,
											@RequestParam String upassword,
											@RequestParam String confirmPassword) {
		
		try {
			
			String emailSelectQuery = "select * from Tusers";
			PreparedStatement pre = db.connect(emailSelectQuery);
			ResultSet rs = pre.executeQuery();
			
			while(rs.next()) {
				
				String dbEmail = rs.getString("umail");
				
				if(dbEmail.equals(umail)) {
					
					model.addAttribute("mailFail","This email has already taken!");
					
					return "registerPage";
					
				}
				
			}
			
			if (!upassword.equals(confirmPassword)) {
				
				model.addAttribute("passwordFail", "Password confirmation err!");
				
				return "registerPage";
				
			}
				
				else {
					
					String insertQuery = "INSERT INTO `tusers` (`uid`, `umail`, `uname`, `usurname`, `uphone`, `upassword`)"
							+ " VALUES (NULL, ?, ?, ?, ?, ?)";
					
					PreparedStatement pr = db.connect(insertQuery);
					pr.setString(1, umail);
					pr.setString(2, uname);
					pr.setString(3, usurname);
					pr.setString(4, uphone);
					pr.setString(5, upassword);
					pr.executeUpdate();
				}
			
			
			
		} catch (Exception e) {
			System.err.println("Email control query err!"+e);
		}
		
		model.addAttribute("registerSuccess", "Register Success!");

		return "registerPage";
		
	}
	
}
