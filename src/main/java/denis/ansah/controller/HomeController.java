package denis.ansah.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import denis.ansah.pojo.Product;
import denis.ansah.pojo.Utils;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login")
	public ModelAndView test2(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		Filter request fields
		if(request.getAttribute("unsafe_request") == "true"){
        	System.out.println("MyInterceptor caught something bad");
            return new ModelAndView("securityError");
        }
		
		System.out.println("doPost");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String operation = request.getParameter("operation");

		if (username.equals("denis") || username.equals("Denis") && password.equals("qqqqqq")) {
			System.out.println("    valid account");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			Cookie usernameCookie = new Cookie("username", username);
			Cookie passwordCookie = new Cookie("password", password);
			usernameCookie.setMaxAge(300); //5min session
			passwordCookie.setMaxAge(300);
			response.addCookie(usernameCookie);
			response.addCookie(passwordCookie);
			return new ModelAndView("storeMenu");
//            response.sendRedirect("storeMenu.jsp");

		} else { 
			System.out.println("invalid account"); 
			return new ModelAndView("loginError");
//            response.sendRedirect("loginError.html");
		}
	}
	
	@RequestMapping(value="/main.shop*")
	public ModelAndView test3(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		List<Product> products = new ArrayList<Product>();
		for (Product product : Utils.createBooks()) { products.add(product); }
		for (Product product : Utils.createMusic()) { products.add(product); }
		for (Product product : Utils.createComputers()) { products.add(product); }
		Set<Product> cartProducts = (HashSet<Product>)session.getAttribute("cartProducts");
        if(cartProducts == null) { session.setAttribute("cartProducts", new HashSet<Product>()); }
        String view = request.getParameter("view");
        if (view.equals("login")) { response.sendRedirect("index.html"); } 
        else if (view.equals("logout")) {
        	session.invalidate();
			return new ModelAndView("index");
//        	response.sendRedirect("index.html");
        }
        else if (view.equals("books")){
			request.setAttribute("books", Utils.createBooks());
			return new ModelAndView("booksView");

//			request.getRequestDispatcher("/booksView.jsp").forward(request, response);
		}
        else if (view.equals("music")){
			request.setAttribute("songs", Utils.createMusic());
			return new ModelAndView("musicView");

//			request.getRequestDispatcher("/musicView.jsp").forward(request, response);
		}
        else if (view.equals("computers")){
			request.setAttribute("computers", Utils.createComputers());
			return new ModelAndView("computersView");

//			request.getRequestDispatcher("/computersView.jsp").forward(request, response);
		}
        else if (view.equals("viewCart")){
//			request.setAttribute("computers", Utils.createComputers());
			return new ModelAndView("viewCart");

//			request.getRequestDispatcher("/computersView.jsp").forward(request, response);
		}
        else if (view.equals("addToCart")) {
        	System.out.println("    addToCart");
       	 	List<Product> addedProducts = new ArrayList<Product>();
        	String[] names = request.getParameterValues("name");
            if(names != null && names.length > 0){
            	for(String name: names) {
            		System.out.println("        " + name);
            		for(Product product: products) {
                		System.out.println("            " + product);
            			if(product.getName().contains(name)) {
            				cartProducts.add(product);
            				addedProducts.add(product);
            			}
            		}
            	}
//    			request.setAttribute("cartProducts", cartProducts);
    			request.setAttribute("addedProducts", addedProducts);
    			return new ModelAndView("productsAdded");

//            	RequestDispatcher dispatcher = request.getRequestDispatcher("/productsAdded.jsp");
//            	dispatcher.forward(request, response);
            } else {
				//redirect to error page or show alert
    			return new ModelAndView("NoProductsAdded");

//            	RequestDispatcher dispatcher = request.getRequestDispatcher("/NoProductsAdded.html");
//            	dispatcher.forward(request, response);
			}
        }
		return new ModelAndView("index");
	}
}
