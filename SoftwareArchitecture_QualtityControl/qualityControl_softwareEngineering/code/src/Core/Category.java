package Core;

import java.util.ArrayList;

import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.requires;

public class Category {
	
	private ArrayList<String> m_categories;
	
	public Category() {
		m_categories = new ArrayList<String>();
		String[] basic_categories = {"Phone", "Tablet", "Computer", "Image", "Sound", "Home & Living", "Kitchen", "Travel", "Fashion", "Sport"};
		for (String cat : basic_categories) {
			m_categories.add(cat);
		}
	}
	
	public ArrayList<String> getCategories() {
		return m_categories;
	}
	
	@requires("category.size() > 0")
	@ensures("m_categories.find(category)")
	public void addCategory(String category) {
		if (!m_categories.contains(category))
			m_categories.add(category);
	}
}