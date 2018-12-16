package Core;

import java.util.HashMap;
import java.util.Map;
import Core.convert;
import be.ac.ua.ansymo.adbc.annotations.*;

public class Cart {
	private int m_cid;
	
	private HashMap<Item, Integer> m_contents;
	
	/**
	 * Create an empty cart (for a new session)
	 */
	public Cart() {
		m_contents = new HashMap<Item, Integer>();
	}
	
	
	/**
	 * Add an item with quantity to the cart
	 * 
	 * @param item
	 * @param quantity
	 */
	 @requires({"quantity > 0","item != null"})
	 @ensures("this.m_contents.get(Item) >= quantity")
	 @advisedBy("login checker (authenticate)")
	public void addItem(Item item, int quantity) {
		if (m_contents.containsKey(item)) {
			m_contents.put(item, m_contents.get(item) + quantity);
		} else {
			m_contents.put(item, quantity);
		}
	}
	
	
	/**
	 * Remove a quantity of an item from the Cart
	 * 
	 * @param item
	 * @param quantity
	 */
	 @requires({"quantity > 0", "item != null",
		 "m_contents.containsKey(Item) != Null"})
	 @advisedBy("login checker (authenticate)")
	public void removeItem(Item item, int quantity) {
		if (m_contents.containsKey(item)) {
			if (m_contents.get(item) >= quantity) {
				m_contents.put(item, m_contents.get(item) - quantity);
			}
		}
	}
	
	
	public HashMap<Item, Integer> contents() {
		return m_contents;
	}
	
	//returns total cost order as float
	@ensures("totalPrice >= 0")
	public float getTotalCostFl() {
	    float totalPrice = 0;
	    for(Map.Entry<Item, Integer> entry : m_contents.entrySet()) {
	        totalPrice += entry.getKey().price() * entry.getValue();
	    }
	    return totalPrice;
	}
	
	//returns total cost of the order as a string
	public String getTotalCostSt() {
	    return convert.priceToString(getTotalCostFl());
	}
}