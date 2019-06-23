package cn.swu.bs.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.swu.bs.book.domain.Book;
import cn.swu.bs.category.domain.Category;
import cn.swu.bs.pager.Expression;
import cn.swu.bs.pager.PageBean;
import cn.swu.bs.pager.PageConstants;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();
	//按分类查询
	public Book findByBid(String bid) throws SQLException{
		String sql = "select * from t_book where bid=?";
		Map<String,Object> map = qr.query(sql, new MapHandler(),bid);
		//把map中除了cid之外的属性映射到book中
		Book book = CommonUtils.toBean(map, Book.class);
		//把map中cid属性映射到category中
		Category category = CommonUtils.toBean(map, Category.class);
		book.setCategory(category);
		return book;
	}
	//按分类查询
	public PageBean<Book> findByCategory(String cid,int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("cid","=",cid));
		return findByCriteria(exprList,pc);
	}
	//按书名模糊查询
	public PageBean<Book> findByBname(String bname,int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("bname","like","%"+bname+"%"));
		return findByCriteria(exprList,pc);
	}
	//按作者查询
	public PageBean<Book> findByAuthor(String author,int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("author","like","%"+author+"%"));
		return findByCriteria(exprList,pc);
	}
	//按出版社
	public PageBean<Book> findByPress(String press,int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("press","like","%"+press+"%"));
		return findByCriteria(exprList,pc);
	}
	//多条件组合查询
	public PageBean<Book> findByCombination(Book criteria,int pc) throws SQLException{
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("bname","like","%"+criteria.getBname()+"%"));
		exprList.add(new Expression("author","like","%"+criteria.getAuthor()+"%"));
		exprList.add(new Expression("press","like","%"+criteria.getPress()+"%"));
		return findByCriteria(exprList,pc);
	}
	//
	//通用查询方法
	private PageBean<Book> findByCriteria(List<Expression> exprList,int pc) throws SQLException {
		//得到ps,tr,beanlist,创建pagebean
		//1得到ps
		int ps = PageConstants.BOOK_PAGE_SIZE;
		//2通过exprList来生成where子句
		//不影响查询
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();//sql中有问号
		for(Expression expr:exprList) {
			//where 1=1 and bid = 
			whereSql.append(" and ").append(expr.getName())
			.append(" ").append(expr.getOperator()).append("");
			if(!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		//System.out.println(whereSql);
		//System.out.println(params);
		//总记录数
		String sql = "select count(*) from t_book" + whereSql;
		Number number = (Number)qr.query(sql, new ScalarHandler(),params.toArray());
		int tr = number.intValue();
		//4.得到BeanList
		sql = "select * from t_book"+whereSql+" order by orderBy limit ?,?";
		params.add((pc-1)*ps);//第一个问号：（当前页-1）× 每页多少记录
		params.add(ps);
		List<Book> beanList = qr.query(sql, new BeanListHandler<Book>(Book.class)
				,params.toArray());
		//创建PageBean,设置参数
		PageBean<Book> pb = new PageBean<Book>();
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		//没有url参数
		return pb;
	}
}
