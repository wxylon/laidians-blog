/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-20
 */
public class ListUtilsTests {
	
	public List<Person> persons = new ArrayList<Person>();
	
	@Before
	public void init(){
		for(int i = 0; i < 100; i++){
			if(i % 2 == 0){
				persons.add(new Person("名字"+i, 10, i));
			}else{
				persons.add(new Person("名字"+i, 11, i));
			}
		}
	}
	
	@Test
	public void testGroup() throws Exception{
		List<List<Person>> lists = ListUtils.group(persons, "age", "mingci");
		for(List<Person> persons : lists){
			for(Person person : persons){
				System.out.println(person.toString());
			}
			System.out.println("=========================");
		}
	}
	
	public class Person{
		private String name;
		private int age;
		private int mingci;
		
		Person(String name, int age, int mingci){
			this.name = name;
			this.age = age;
			this.mingci = mingci;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getMingci() {
			return mingci;
		}

		public void setMingci(int mingci) {
			this.mingci = mingci;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", age=" + age + ", mingci=" + mingci + "]";
		}
	}
}


