package com.websystique.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.websystique.springboot.model.User;



@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public User loginWithUserName(String userName, String password) {
		for(User user : users){
			if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Sam",30, 70000, "sam", "1", "中田", "https://i.pinimg.com/originals/c3/d4/3e/c3d43ed603fc6f4b8385520db69b6231.jpg"));
		users.add(new User(counter.incrementAndGet(),"Tom",40, 50000, "tom", "2", "増原", "https://media.istockphoto.com/vectors/gentleman-icon-set-vector-id585293894"));
		users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000, "jerome", "3", "小野", "https://previews.123rf.com/images/dervish37/dervish371407/dervish37140700041/30033253-icon-of-the-gentleman.jpg"));
		users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000, "silvia", "4", "", "https://cdn.vectorstock.com/i/thumb-large/43/55/a-modern-avatar-of-a-gentleman-vector-17924355.jpg"));
		users.add(new User(counter.incrementAndGet(),"giang",50, 40000, "giang", "giang123", "ジァン", "https://t3.ftcdn.net/jpg/00/92/51/08/500_F_92510885_Im4j6cUajyto6uzo8uDR1XYJNmTSQ49Y.jpg"));
		return users;
	}

}
