package cn.swu.bs.book.service;

import java.sql.SQLException;

import cn.swu.bs.book.dao.BookDao;
import cn.swu.bs.book.domain.Book;
import cn.swu.bs.pager.PageBean;

public class BookService {
	private BookDao bookDao = new BookDao();
	//加载
	public Book load(String bid) {
		try {
			return bookDao.findByBid(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	//按分类查询
	public PageBean<Book> findByCategory(String cid,int pc){
		try {
			return bookDao.findByCategory(cid, pc);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//按书名查询
	public PageBean<Book> findByBname(String bname,int pc){
		try {
			return bookDao.findByBname(bname, pc);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//按作者查询
	public PageBean<Book> findByAuthor(String author,int pc){
		try {
			return bookDao.findByAuthor(author, pc);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//按出版社查询
	public PageBean<Book> findByPress(String press,int pc){
		try {
			return bookDao.findByPress(press, pc);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//多条件组合查询
	public PageBean<Book> findByCombination(Book criteria,int pc){
		try {
			return bookDao.findByCombination(criteria, pc);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
