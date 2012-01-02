package org.integration.connectors.documentfiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PagedList<T> implements Iterable<T> {
	
	private int total;
	
	private List<T> items = new ArrayList<T>();
	
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void addItem(T item) {
		items.add(item);
	}

	@Override
	public Iterator<T> iterator() {
		return items.iterator();
	}
	
}
