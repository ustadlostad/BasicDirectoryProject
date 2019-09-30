package com.calisma.baturhw;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import utilities.DB;
import utilities.SessionControl;

@Controller
public class CartDeleteController {
	
	DB db = new DB();
	
	@RequestMapping(value = "/delete/{param}")
	public String cartDelete(Model model,HttpServletRequest req,@PathVariable int param ) {
		
		String deleteQuery = "delete from `cart` where `cart`.`crid` = ?";
		
		try {
			
			PreparedStatement preDelete = db.connect(deleteQuery);
			preDelete.setInt(1, param);
			
			int deleteStatu = preDelete.executeUpdate();
			
			if(deleteStatu>0) {
				
				model.addAttribute("Cart Deleted!","deleteSuccess");
				
				return "redirect:/cartInsertPage";
				
			}else {
				
				model.addAttribute("Cart Delete Error!","deleteError");
				
			}
			
		} catch (Exception e) {
			System.err.println("Delete Err!(DB) : "+e);
		}
		
		return SessionControl.sessionContoller(req, "cartInsertPage");
	}

}
