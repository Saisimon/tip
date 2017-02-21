package net.saisimon.tomcat.util;

import java.util.HashMap;
import java.util.Map;

public final class ParameterMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -35810552721922453L;
	
	private boolean locked;
	
	public ParameterMap() {
		super();
	}
	
	public ParameterMap(int initialCapacity) {
		super(initialCapacity);
	}
	
	public ParameterMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}
	
	public ParameterMap(Map<K, V> map) {
		super(map);
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public V put(K key, V value) {
		if (locked) {
			throw new IllegalStateException("不可修改");
		}
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		if (locked) {
			throw new IllegalStateException("不可修改");
		}
		super.putAll(m);
	}

	@Override
	public V remove(Object key) {
		if (locked) {
			throw new IllegalStateException("不可修改");
		}
		return super.remove(key);
	}

	@Override
	public void clear() {
		if (locked) {
			throw new IllegalStateException("不可修改");
		}
		super.clear();
	}
	
}
