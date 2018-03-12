package com.websystique.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	private long id;
	
	private String name;
	
	private int age;
	
	private double salary;

	private String userName;

	private String password;

	private String jpName;

	private String avatarUrl;

	private String userId;

	public User(){
		id=0;
	}

	public User(long id, String name, int age, double salary){
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public User(long id, String name, int age, double salary, String userId, String userName, String password, String jpName, String avatarUrl){
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.jpName = jpName;
		this.avatarUrl = avatarUrl;
	}

	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@JsonIgnore
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

//	@JsonIgnore
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJpName() {
		return jpName;
	}

	public void setJpName(String jpName) {
		this.jpName = jpName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
        return id == other.id;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + "]";
	}


}
