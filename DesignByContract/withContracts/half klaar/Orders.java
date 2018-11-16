package Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	//require !m_id_order.find(order.ID())
	//advised by authenticator: changer must be admin or owner of orders and order
	public void addOrder(Order order) {
		m_orders.add(order);
		m_id_order.put(order.ID(), order);
	}
	
	public List<Order> getAllOrders() {
		return m_orders;
	}
	
	//require order exists in m_id_order
	public Order getOrder(int oid) {
		return m_id_order.get(oid);
	}
	
	//require order exists in m_id_order
	public Order_Status getOrderStatus(int oid) {
		return m_id_order.get(oid).getStatus();
	}
	
	//require oid to exists in m_id_order 
	//ensure order erased from m_orders and m_id_order
	//advised by authenticator: cancellor must be admin or owner of orders
	public boolean requestOrderCancel(int oid) {
		for (Order o : m_orders) {
			if (o.ID() == oid) {
				return o.requestCancel();
			}
		}
		return false;
	}
	
	
}
