package Core;

import java.util.HashSet;

import be.ac.ua.ansymo.adbc.annotations.requires;
import be.ac.ua.ansymo.adbc.annotations.ensures;

public class Item {
	private int m_iid;
	
	private String m_name;
	private String m_desc;
	private float m_price;
	private HashSet<String> m_category;
	
	/**
	 * Create new Item
	 * 
	 * @param name
	 * @param desc
	 * @param price
	 */
	 @requires({"name.size>0","price>0"})
	 @ensures({"this.name() == name "
	 		+ "&& this.desc() == desc "
	 		+ "&& this.price == price"})
	public Item(String name, String desc, float price) {
		
	}
	
	public int ID() {
		return m_iid;
	}
	
	public String name() {
		return m_name;
	}
	
	public String desc() {
		return m_desc;
	}
	
	public float price() {
		return m_price;
	}
	
	public HashSet<String> category() {
		return m_category;
	}
}