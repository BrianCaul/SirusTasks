package com.sirustasks.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sirustasks.model.User;
import com.sirustasks.service.UserService;

@Controller
@SessionAttributes("user")
public class UserRestController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/users", method = RequestMethod.GET)
	public ModelAndView getAllUsers() {
		List<User> users = null;
		Map<String, List<User>> results = new HashMap<String, List<User>>();

		try {
			users = userService.getAllUsers();
		} catch (Exception e) {
			String sMessage = "Error invoking users";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		results.put("users", users);

		return new ModelAndView(jsonView, DATA_FIELD, results);
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.DELETE)
	public ModelAndView deleteUser(@PathVariable String userid) {

		try {
			userService.deleteUser(userid);
		} catch (Exception e) {
			String sMessage = "Error invoking users";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD,
				"Sucessfully Deleted ID: " + userid);
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable String userid) {
		User user = new User();
		try {
			user = userService.findByUserId(userid);
		} catch (Exception e) {
			String sMessage = "Error invoking user";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, user);
	}

	@RequestMapping(value = "/rest/users", method = RequestMethod.POST)
	public ModelAndView createUser(@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("userType") String userType,
			@RequestParam("password") String password,
			@RequestParam("username") String userName,
			@RequestParam("companyId") String companyId) {

		User user = new User();
		if (userService.findByUserName(userName)) {
			String sMessage = "User Name exists. Try another user name";
			return getErrorJSON(String.format(sMessage));
		} else {

			try {
				user.setPhone(phone);
				user.setEmail(email);
				user.setName(name);
				user.setUserType(userType);
				user.setPassword(password);
				user.setUserName(userName);
				user = userService.createUser(user);
			} catch (Exception e) {
				String sMessage = "Error creating user";
				return getErrorJSON(String.format(sMessage, e.toString()));
			}

		}

		return new ModelAndView(jsonView, DATA_FIELD, user);
	}
	
	
	@RequestMapping(value = "/rest/users/signin", method = RequestMethod.POST)
	public ModelAndView signIn(@RequestParam("uname") String userName, @RequestParam("pass") String password) {
			User user = new User();
			try {
				user = userService.findByLogin(userName, password);
			} catch (Exception e) {
				String sMessage = "Error logging in user";
				return getErrorJSON(String.format(sMessage, e.toString()));
			}
			
			if(user == null || user.getId() == 0){
				String sMessage = "User credentials are incorrect";
				return getErrorJSON(String.format(sMessage));
			}

		return new ModelAndView(jsonView, DATA_FIELD, user);
	}

	@RequestMapping(value = "/rest/users/{userid}", method = RequestMethod.POST)
	public ModelAndView updateUser(@PathVariable String userid,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			@RequestParam("userType") String userType,
			@RequestParam("password") String password,
			@RequestParam("username") String userName,
			@RequestParam("companyId") String companyId) {

		User user = new User();
		try {
			user = userService.findByUserId(userid);

			user.setPhone(phone);
			user.setEmail(email);
			user.setName(name);
			user.setUserType(userType);
			user.setPassword(password);
			user.setUserName(userName);
			user = userService.updateUser(user);
		} catch (Exception e) {
			String sMessage = "Error creating user";
			return getErrorJSON(String.format(sMessage, e.toString()));
		}

		return new ModelAndView(jsonView, DATA_FIELD, user);
	}
	

	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 * @return
	 */
	private ModelAndView getErrorJSON(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}
}
