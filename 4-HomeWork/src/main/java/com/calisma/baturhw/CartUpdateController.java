package com.calisma.baturhw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import properties.TableProperties;
import utilities.DB;
import utilities.SessionControl;

@Controller
public class CartUpdateController {
	
	DB db = new DB();
	
	int crid2;
	
	@RequestMapping(value = "/updatePage/{crid}", method = RequestMethod.GET)
	public String cartUpdatePage(Model model, HttpServletRequest req, @PathVariable int crid) {
		
		crid2 = crid;
		System.out.println("id : "+crid);
		model.addAttribute("tableProperties", tableResult(crid));
		model.addAttribute("categoryProperties", new CartInsertController().categoryResult());
		
	
		return SessionControl.sessionContoller(req, "updatePage");
	
	}
	
	public TableProperties tableResult(int crid){
		
		try {
			
			String selectQuery = "select * from cart c inner join categories as ct on c.cid = ct.cid where crid = ?";
			
			PreparedStatement preSelect = db.connect(selectQuery);
			preSelect.setInt(1,crid);
			ResultSet rs = preSelect.executeQuery();
			while(rs.next()) {
				
				TableProperties tp = new TableProperties();
				tp.setCname(rs.getString("cname"));
				tp.setCsurname(rs.getString("csurname"));
				tp.setCphone(rs.getString("cphone"));
				tp.setCmail(rs.getString("cmail"));
				tp.setCnote(rs.getString("cnote"));
				tp.setCrid(rs.getInt("crid"));
				tp.setCtitle(rs.getString("ctitle"));
				tp.setCid(rs.getInt("cid"));
				return tp;
				
			}
			
		} catch (Exception e) {
			System.err.println("table select query err! : "+e);
		}
		
		return null;
		
	}
	
	
	@RequestMapping(value = "/cartUpdate", method = RequestMethod.POST)
	public String cartUpdate(TableProperties tbl, HttpServletRequest req) {
		
		System.out.println("crid : "+crid2);
		String updateQuery = "update cart set cname = ?, csurname = ?, cphone = ?, cmail = ?, cnote = ?, cid = ? where crid = ? ";
		
		try {
			
			PreparedStatement pre = db.connect(updateQuery);
			pre.setString(1, tbl.getCname());
			pre.setString(2, tbl.getCsurname());
			pre.setString(3, tbl.getCphone());
			pre.setString(4, tbl.getCmail());
			pre.setString(5, tbl.getCnote());
			pre.setInt(6, tbl.getCid());
			pre.setInt(7, crid2);
			
			pre.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("Cart Update Err : "+e);
		}
		
		
		return SessionControl.sessionContoller(req, "redirect:/cartInsertPage");
		
	}

}
