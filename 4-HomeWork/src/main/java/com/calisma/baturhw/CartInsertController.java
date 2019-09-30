package com.calisma.baturhw;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import properties.CartProperties;
import properties.CategoryProperties;
import properties.TableProperties;
import utilities.DB;
import utilities.SessionControl;

@Controller
public class CartInsertController {
	
	DB db = new DB();
	
		@RequestMapping(value = "/cartInsertPage", method = RequestMethod.GET)
		public String dashboard(Model model,HttpServletRequest req) {
			
			model.addAttribute("categoryList",categoryResult());
			model.addAttribute("tableResult",tableResult());
		
			return SessionControl.sessionContoller(req, "cartInsertPage");
		}
		
		public List<CategoryProperties> categoryResult(){
			
			List<CategoryProperties> ls = new ArrayList<CategoryProperties>();
			
			try {
				
				String categorySelectQuery = "SELECT * FROM categories";
				PreparedStatement preCategory = db.connect(categorySelectQuery);
				ResultSet rs = preCategory.executeQuery();
				while(rs.next()) {
					
					int cid = rs.getInt("cid");
					String ctitle = rs.getString("ctitle");
					
					CategoryProperties cp = new CategoryProperties();
					cp.setCid(cid);
					cp.setCtitle(ctitle);
					
					ls.add(cp);
					
				}
				
			} catch (Exception e) {
				System.err.println("Category sql err : "+e);
			}
			
			return ls;
		}
		
		@RequestMapping(value = "/cartInsert", method = RequestMethod.POST)
		public String cartInsert(Model model, HttpServletRequest req,CartProperties cr) {
			
			try {
				
				//CartProperties cr = new CartProperties();
				
				String insertQuery = "INSERT INTO `cart` (`crid`, `cname`, `csurname`, `cphone`, `cmail`, `cnote`, `cid`) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preInsert = db.connect(insertQuery);
				preInsert.setString(1, cr.getCname());
				preInsert.setString(2, cr.getCsurname());
				preInsert.setString(3, cr.getCphone());
				preInsert.setString(4, cr.getCmail());
				preInsert.setString(5, cr.getCnote());
				preInsert.setInt(6, cr.getCid());
				
				preInsert.executeUpdate();
				
				
				
			} catch (Exception e) {
				System.err.println("DB insert err : "+e);
			}
			
			return SessionControl.sessionContoller(req, "redirect:/cartInsertPage");
		}
		
		public List<TableProperties> tableResult(){
			
			List<TableProperties> ls = new ArrayList<TableProperties>();
			
			try {
				
				String selectQuery = "select * from cart c inner join categories as ct on c.cid = ct.cid";
				PreparedStatement preSelect = db.connect(selectQuery);
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
					ls.add(tp);
					
				}
				
			} catch (Exception e) {
				System.err.println("table select query err! : "+e);
			}
			
			return ls;
			
		}
		
		

}
	

