package Core;

import java.util.HashMap;

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
	 //require quantity > 0
	 //advised by login checker (authenticate)
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
	 //require quantity >= 0
	 //advised by login checker (authenticate)
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
	
	//ensure totalPrice >= 0
	public float getTotalCostFl() {
	    float totalPrice = 0;
	    for(HashMap::iterator it = m_contents.begin(); it != m_contents.end(); it++){
	        totalPrice += it->first.price() * it->second;
	    }
	    return totalPrice
	}
	
	public string getTotalCostSt() {
	    return convert(getTotalCostFl())
	}
}
