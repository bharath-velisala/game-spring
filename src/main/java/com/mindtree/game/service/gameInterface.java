package com.mindtree.game.service;

import java.util.List;

import com.mindtree.game.entity.Player;
import com.mindtree.game.entity.Team;
import com.mindtree.game.exceptions.GameException;

public interface gameInterface {
	boolean addTeam(Team team)throws GameException;
	boolean addPlayer(Player player,int teamId) throws GameException;
	public List<Player>getAllPlayerByTeam(String TeamName) throws GameException;
	boolean updateTeamById(int teamId,String Location) throws GameException;
	boolean deletePlayerById(int playerId) throws GameException;

}
