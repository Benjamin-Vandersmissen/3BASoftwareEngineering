package Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Catalog {
	private HashMap<Integer, Item> m_all_items;
	
	/**
	 * Create new, empty Catalog
	 */
	public Catalog() {
		
	}
	
	
	/**
	 * Load existing catalog from file
	 * 
	 * @param filename
	 */
	 //require filename.size() > 0
	 //test if file exists
	 //advised by login checker (authenticate)
	public Catalog(String filename) {
		
	}
	
	
	/**
	 * Store the current catalog to file
	 * 
	 * @param filename
	 */
	 //require filename.size() > 0
	 //advised by login checker (authenticate)
	public void store(String filename) {
		
	}
	
	/**
	 * Voeg item toe aan de catalogus
	 * 
	 * @param item
	 */
	//ensure m_all_itmes.find(item.ID()) == item)
	//ensure m_all_items no duplicates
	//advised by login checker or Client (authenticate) (must be admin)
	public void addItem(Item item) {
		m_all_items.put(item.ID(), item);
	}
	
	public int getNumberOfItems() {
		return m_all_items.size();
	}
	
	public Item getItemByID(int id) {
		return m_all_items.get(id);
	}
	
	public Set<Integer> getAllIDs() {
		return m_all_items.keySet();
	}
	
	//ensure all filtered items in all categories
	public ArrayList<Item> filterCategories(HashSet<String> categories) {
		ArrayList<Item> filtered_items = new ArrayList<Item>();
		
		for (Integer iid : m_all_items.keySet()) {
			HashSet<String> check = new HashSet<String>(categories);
			check.retainAll(m_all_items.get(iid).category());
			if (check.size() > 0)
				filtered_items.add(m_all_items.get(iid));
		}
		
		return filtered_items;
	}
}
