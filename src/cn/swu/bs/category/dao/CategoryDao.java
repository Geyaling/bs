package cn.swu.bs.category.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.swu.bs.category.domain.Category;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();
	/*
	 * 将Map里的数据映射到Category中
	 */
	private Category toCategory(Map<String,Object> map) {
		//map (cid:xx ,cname:xx,pid:xx,desc:xx,orderBy:xx)
		//Category(cid:xx,cname:xx,parent:xx,desc:xx)
		Category category = CommonUtils.toBean(map, Category.class);
		//数据库中的pid属性丢了，需要赋值
		String pid = (String)map.get("pid");
		if(pid != null) {
			//如果父分类pid不为空
			//使用一个父分类对象来拦截pid
			//再把父分类设置给category
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		return category;
	}
	private List<Category> toCategoryList(List<Map<String,Object>> mapList){
		List<Category> categoryList = new ArrayList<Category> ();
		for(Map<String,Object> map : mapList) {
			Category c = toCategory(map);
			categoryList.add(c);
		}
		return categoryList;
	}
	public List<Category> findAll() throws SQLException{
		String sql = "select * from t_category where pid is null";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parents = toCategoryList(mapList);
		//循环遍历所有一级分类
		for(Category parent:parents) {
			List<Category> children = findByParent(parent.getCid());
			parent.setChildren(children);
		}
		return parents;
	}
	//通过父分类查询子分类
	public List<Category> findByParent(String pid) throws SQLException{
		String sql = "select * from t_category where pid = ?";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),pid);
		return toCategoryList(mapList);
	}
}
