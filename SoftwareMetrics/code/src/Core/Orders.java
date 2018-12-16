package Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.ac.ua.ansymo.adbc.annotations.requires;
import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.advisedBy;

public class Orders {
	private List<Order> m_orders;
	private HashMap<Integer, Order> m_id_order;
	
	/**
	 * Create empty order history
	 */
	public Orders() {
		m_orders = new ArrayList<Order>();
		m_id_order = new HashMap<Integer, Order>();
	}

	@requires("!m_id_order.find(order.ID()")
	@ensures({"m_id_order.get(order.ID()) == order", "m_orders.contains(order)"})
	@advisedBy("authenticator: changer must be admin or owner of orders and order")
	public void addOrder(Order order) {
		m_orders.add(order);
		m_id_order.put(order.ID(), order);
	}
	
	public List<Order> getAllOrders() {
		return m_orders;
	}
	
	public Order getOrder(int oid) {
		return m_id_order.get(oid);
	}
	
	@requires("m_id_order.get(oid) != null")
	public Order_Status getOrderStatus(int oid) {
		return m_id_order.get(oid).getStatus();
	}
	
	@requires("m_id_order.get(oid) != null") 
	@ensures({"m_orders.contains(order) == false", 
			"m_id_order.contains(order.ID()) == false"})
	@advisedBy("authenticator: cancellor must be admin or owner of orders")
	public boolean requestOrderCancel(int oid) {
		for (Order o : m_orders) {
			if (o.ID() == oid) {
				return o.requestCancel();
			}
		}
		return false;
	}
	
	
}