package com.mindtree.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.game.entity.Player;
import com.mindtree.game.entity.Team;
import com.mindtree.game.exceptions.GameException;
import com.mindtree.game.service.impl.ServiceImpl;

@RestController
@RequestMapping(path = "/game")
public class MainController {
	static String msg = "";

	@Autowired
	public ServiceImpl service;

	@PostMapping("/add/team")
	public ResponseEntity<?> addTrack(@RequestBody Team team) {
		boolean flag = false;
		try {
			flag = service.addTeam(team);
		} catch (GameException e) {
			msg = e.getLocalizedMessage();
		}
		if (flag == true) {
			return new ResponseEntity<>("Team Created Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/add/player/{teamId}")
	public ResponseEntity<?> addPlayer(@RequestBody Player player, @PathVariable Integer teamId) {
		boolean flag = false;
		try {
			flag = service.addPlayer(player, teamId);

		} catch (GameException e) {
			msg = e.getLocalizedMessage();
		}

		if (flag == true) {
			return new ResponseEntity<>("Player Created Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/all/{teamName}")
	public ResponseEntity<?> getPlayerDetailsByTeam(@PathVariable String teamName) {
		try {
			return new ResponseEntity<>(service.getAllPlayerByTeam(teamName), HttpStatus.OK);
		} catch (GameException e) {
			msg = e.getLocalizedMessage();
		}
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update/{teamId}")
	public ResponseEntity<?> updateTeam(@RequestBody String location, @PathVariable Integer teamId) {
		boolean flag = false;
		try {
			flag = service.updateTeamById(teamId, location);

		} catch (GameException e) {
			msg = e.getLocalizedMessage();
		}
		if (flag == true) {
			return new ResponseEntity<>("Updated Succesfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/player/{playerId}")
	public ResponseEntity<?> deleteTrack(@PathVariable Integer playerId) {
		boolean flag = false;
		try {
			flag = service.deletePlayerById(playerId);

		} catch (GameException e) {
			msg = e.getLocalizedMessage();
		}
		if (flag == true) {
			return new ResponseEntity<>("Deleted Player Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
	}

}
