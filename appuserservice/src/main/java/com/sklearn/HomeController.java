package com.sklearn;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sklearn.dto.UserDTO;
import com.sklearn.service.UserService;
import com.sklearn.uimodel.CreateUsrResModel;
import com.sklearn.uimodel.UserModel;

@RestController
@RequestMapping("/user")
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/healthcheck")
	public ResponseEntity<String> statuscheck() {
		return new ResponseEntity<String>("Working", HttpStatus.OK);
	}

	@PostMapping("/createuser")
	public ResponseEntity<CreateUsrResModel> createUser(@RequestBody UserModel userModel) {
		ModelMapper mm = new ModelMapper();
		mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDTO userDto = mm.map(userModel, UserDTO.class);
		
		UserDTO userDtoToReturn = userService.createUser(userDto);
		CreateUsrResModel curm = mm.map(userDtoToReturn, CreateUsrResModel.class); 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(curm);
	}
}
