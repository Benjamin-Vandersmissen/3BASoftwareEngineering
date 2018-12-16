package Core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import be.ac.ua.ansymo.adbc.annotations.*;

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
	 @requires({"filename.size() > 0", "file exists"})
	 @ensures("file is loaded")
	 @advisedBy("login checker (authenticate)")
	public Catalog(String filename) {
		
	}
	
	
	/**
	 * Store the current catalog to file
	 * 
	 * @param filename
	 */
	 @requires({"filename.size() > 0", "file exists"})
	 @advisedBy("login checker (authenticate)")
	public void store(String filename) {
		
	}
	
	/**
	 * Voeg item toe aan de catalogus
	 * 
	 * @param item
	 */
	@requires({"item != null" ,"this.checkDuplicates(item) == false"})
	@advisedBy("login checker or Client (authenticate) (must be admin)")
	public void addItem(Item item) {
		m_all_items.put(item.ID(), item);
	}
	
	@requires("item != null")
	public boolean checkDuplicates(Item item) {
	    for(Map.Entry<Integer, Item> it : m_all_items.entrySet()) {
	    	if(it.getValue().name() == item.name()
	    			&& it.getValue().desc() == item.desc()
	    			&& it.getValue().price() == item.price()) {
	    		return true;
	    	}
	    }
	    return false;
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
	
	@ensures("this.getCommonCategories(filtered_items).containsAll(categories)")
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
	
	@requires("items != null")
	public HashSet<String> getCommonCategories(ArrayList<Item> items){
		if(items.size() == 0) {
			return new HashSet<String>();
		}
		if(items.size() == 1) {
			return items.get(0).category();
		}
		HashSet<String> common = new HashSet<String>();
		//deep copy categories from first item
		for(String cat : items.get(0).category()) {
			common.add(cat);
		}
		//for all items:
		for(Item it : items) {
			//remove category from categories if not in item doesn't belong to the category
			for(String cat : common) {
				if(it.category().contains(cat) == false) {
					//remove category from common
					common.remove(cat);
				}
			}
		}
		return common;
	}
}