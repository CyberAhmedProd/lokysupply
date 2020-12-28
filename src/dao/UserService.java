package dao;

import models.User;

public interface UserService {
	
	public User login(String login, String password);
	public boolean register(User p);

}
