package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		
		Class<Person> personClass = Person.class;
		
		System.out.println(personClass.getName());

		Person personObject = new Person();
		Object object = new Object();
		
		/*
		 * Alternative to instanceof operator
		 */
		System.out.println(personClass.isInstance(personObject));
		System.out.println(personClass.isInstance(object));

		System.out.println("\nMethods:");
		Method[] methods = personClass.getMethods();
		for(Method method : methods){
			System.out.println(method);
		}

		System.out.println("\nConstructors:");
		Constructor<Person>[] constructors = (Constructor<Person>[]) personClass.getConstructors();
		for(Constructor<Person> constructor : constructors){
			System.out.println(constructor);
		}
		
		//Accessing private things
		System.out.println("\nFields:");
		Field[] fields = personClass.getDeclaredFields();
		for(Field field : fields){
			System.out.println(field);
		}
		
		/*
		 * Invoke arg constructor
		 * 
		 * Manipulate private variables
		 */
		Constructor<Person> argConstructor = personClass.getConstructor(String.class, int.class);
		
		Person john = argConstructor.newInstance("John", 29);
		System.out.println(john);
		Field nameField = john.getClass().getDeclaredField("name");
		
		nameField.setAccessible(true);

		nameField.set(john, "Amy");
		System.out.println(john);
		
		
		
	}
}
