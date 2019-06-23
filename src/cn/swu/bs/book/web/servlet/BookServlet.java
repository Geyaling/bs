package cn.swu.bs.book.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.swu.bs.book.domain.Book;
import cn.swu.bs.book.service.BookService;
import cn.swu.bs.pager.PageBean;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookServlet extends BaseServlet {
	
	private BookService bookService = new BookService();
	//获取当前页码
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()){
			try {
				pc = Integer.parseInt(param);
			}catch(RuntimeException e) {}
		}
		return pc;
	}
	//截取url，分页导航中需要使用作为超链接的目标
	//http://localhost:8080/bs/BookServlet?method=findByCategory&cid=xxxx
	//  /bs/BookServlet
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI()+"?"+req.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index!=-1) {
			url = url.substring(0,index);
		}
		return url;
	}
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到pc,如果页面传递使用页面传递的，没有为一
		int pc = getPc(req);
		
		//得到url
		String url = getUrl(req);
		//获取查询条件，本方法就是cid
		String cid = req.getParameter("cid");
		//使用pc和cid调PageBean<T>的findByCategory  
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		//给PageBean设置URL,保存PageBean,转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	public String findByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到pc,如果页面传递使用页面传递的，没有为一
		int pc = getPc(req);
		
		//得到url
		String url = getUrl(req);
		//获取查询条件，本方法就是cid
		String author = req.getParameter("author");
		//使用pc和cid调PageBean<T>的findByCategory  
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		//给PageBean设置URL,保存PageBean,转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}

	public String findByPress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到pc,如果页面传递使用页面传递的，没有为一
		int pc = getPc(req);
		
		//得到url
		String url = getUrl(req);
		//获取查询条件，本方法就是cid
		String press = req.getParameter("press");
		//使用pc和cid调PageBean<T>的findByCategory  
		PageBean<Book> pb = bookService.findByPress(press, pc);
		//给PageBean设置URL,保存PageBean,转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	public String findByBname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到pc,如果页面传递使用页面传递的，没有为一
		int pc = getPc(req);
		
		//得到url
		String url = getUrl(req);
		//获取查询条件，本方法就是cid
		String bname = req.getParameter("bname");
		//使用pc和cid调PageBean<T>的findByCategory  
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		//给PageBean设置URL,保存PageBean,转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	//多条件组合查询
	public String findByCombination(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到pc,如果页面传递使用页面传递的，没有为一
		int pc = getPc(req);
		
		//得到url
		String url = getUrl(req);
		//获取查询条件，本方法就是cid
		Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
		//使用pc和cid调PageBean<T>的findByCategory  
		PageBean<Book> pb = bookService.findByCombination(criteria, pc);
		//给PageBean设置URL,保存PageBean,转发到/jsps/book/list.jsp
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}

	public String load(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		req.setAttribute("book", book);
		return "f:/jsps/book/desc.jsp";
	}

}
