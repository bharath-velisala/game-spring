package com.mindtree.game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer playerId;
	private String playerName;
	private Integer age;
	private String Role;
	
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team;
	public Integer getPlayerId() {
		return playerId;
	}
	public Player(Integer playerId, String playerName, Integer age, String role, Team team) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.age = age;
		this.Role = role;
		this.team = team;
	}
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	@JsonBackReference
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	
}
