package com.studentManagement.service;

import com.studentManagement.entity.User;

public interface UserService {

	public User saveUser(User user);

	public void deleteUser(int id);

	public void deleteByUserId(String userId);


	public void removeSessionMessage();

}
