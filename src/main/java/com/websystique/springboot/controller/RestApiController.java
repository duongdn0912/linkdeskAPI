package com.websystique.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.websystique.springboot.model.SeatInfo;
import com.websystique.springboot.service.CheckinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.model.User;
import com.websystique.springboot.model.UserInfo;
import com.websystique.springboot.service.UserService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService;

	@Autowired
	CheckinService checkinService;

	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserInfo userInfo, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", userInfo);


		User user = userService.loginWithUserName(userInfo.getUsername(), userInfo.getPassword());
		if (user == null) {
			logger.error("User with not found.");
			return new ResponseEntity(new CustomErrorType("User not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkin/", method = RequestMethod.POST)
	public ResponseEntity<?> checkin(@RequestBody SeatInfo seatInfo, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", seatInfo);


		Boolean isCheckInSuccess = checkinService.checkIn(seatInfo.getSeatNumber(), seatInfo.getUserNumber());
		if (!isCheckInSuccess) {
			return new ResponseEntity(new CustomErrorType("Check in failed"), HttpStatus.NOT_ACCEPTABLE);
		}
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", isCheckInSuccess);
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/reset-seat/", method = RequestMethod.POST)
	public ResponseEntity<?> resetSeat() {
		logger.info("resetSeat");
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", checkinService.resetSeat());
		return new ResponseEntity<Map<String, Boolean>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-seats/", method = RequestMethod.GET)
	public ResponseEntity<?> getSeats() {
		logger.info("getSeats");
		Map<String, String[]> response = new HashMap<>();
		response.put("seat-datas", checkinService.getSeatDummyDatas());
		return new ResponseEntity<Map<String, String[]>>( response, HttpStatus.OK);
	}
}
