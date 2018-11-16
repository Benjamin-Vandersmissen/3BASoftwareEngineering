package Core;

import java.util.HashMap;

import Core.Clients.Client;

public class Order {
	private int m_oid;
	private static int m_next_id = 1;

	private Order_Status m_status = Order_Status.STARTED;
	
	private Client m_client;
	private HashMap<Item, Integer> m_contents;

	
	/**
	 * Create a new Order from a User's Cart
	 */
	 //require client owns cart
	 //require not cart.contents.isEmpty()
	 //advisedBy authenticator
	public Order(Cart cart, Client client) {
		m_oid = m_next_id;
		m_next_id++;
		
		m_client = client;
		m_contents = cart.contents();
	}
	
	public HashMap<Item, Integer> getItems() {
		return m_contents;
	}
	
	public int ID() {
		return m_oid;
	}
	
	public Client getClient() {
		return m_client;
	}
	
	public Order_Status getStatus() {
		return m_status;
	}
	
	//advised by authenticator (if client: must own order else must be admin)
	public boolean requestCancel() {
		if (m_status != Order_Status.DELIVERED) {
			m_status = Order_Status.CANCELED;
			return true;
		} else {
			return false;
		}
	}
	
	
	
}