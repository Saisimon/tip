package net.saisimon.example;

import java.lang.reflect.ParameterizedType;

/**
 * 定义一个抽象的父类
 * 获取父类中的泛型类型 T
 */
public abstract class SuperClass<T> {

    // 泛型类型
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
	public SuperClass() {
        super();
        // 根据实现类反射获取包含泛型的父类，然后获取泛型的类型
        this.clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    public static void main(String[] args) {
        SuperClass<String> superClassString = new SuperClass<String>(){};
        System.out.println(superClassString.getClazz());

        SuperClass<Entity> superClassEntity = new SuperClass<Entity>(){};
        System.out.println(superClassEntity.getClazz());
    }
    
}

class Entity {
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
