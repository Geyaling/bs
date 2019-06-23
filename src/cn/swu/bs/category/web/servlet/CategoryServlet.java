package cn.swu.bs.category.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.swu.bs.category.domain.Category;
import cn.swu.bs.category.service.CategoryService;
import cn.swu.bs.user.domain.User;
import cn.swu.bs.user.service.exception.UserException;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	//查询所有分类
	public String findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/jsps/left.jsp";
	}	
}
