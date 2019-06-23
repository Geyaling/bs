package cn.swu.bs.category.service;

import java.sql.SQLException;
import java.util.List;

import cn.swu.bs.book.domain.Book;
import cn.swu.bs.category.dao.CategoryDao;
import cn.swu.bs.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	//查询所有分类
	public List<Category> findAll(){
		try {
			return categoryDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
