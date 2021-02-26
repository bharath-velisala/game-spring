package com.mindtree.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.game.entity.Player;
import com.mindtree.game.entity.Team;
import com.mindtree.game.exceptions.GameException;
import com.mindtree.game.exceptions.ResourceNotFoundException;
import com.mindtree.game.repository.PlayerRepository;
import com.mindtree.game.repository.TeamRepository;
import com.mindtree.game.service.gameInterface;
@Service
public class ServiceImpl implements gameInterface {

	@Autowired
	private TeamRepository teamrepo;

	@Autowired
	private PlayerRepository playerrepo;

	@Override
	public boolean addTeam(Team team) throws GameException {
		try {
			teamrepo.save(team);
			return true;
		} catch (Exception e) {
			throw new GameException(e);
		}
	}

	@Override
	public boolean addPlayer(Player player, int teamId) throws GameException {
		Team team=null;
		try {
			team = teamrepo.findById(teamId).orElseThrow(()-> new ResourceNotFoundException("team not found with id: "+teamId));
		} catch (ResourceNotFoundException e) {
			throw new GameException(e);
		}
		if(team!=null) {
			player.setTeam(team);
			playerrepo.save(player);
			return true;
		}else {
		return false;
		}
	}

	@Override
	public List<Player> getAllPlayerByTeam(String teamName) throws GameException {
		List<Player> players = playerrepo.getPlayerByTeam(teamrepo.getTeamByTeamName(teamName));

		if (players.isEmpty()) {
			throw new GameException("team name not found ");
		}
		return players;
	}

	@Override
	public boolean updateTeamById(int teamId, String Location) throws GameException {
		Team team=null;
		try {
			team = teamrepo.findById(teamId).orElseThrow(()-> new ResourceNotFoundException("team not found with id:"+teamId));
		} catch (ResourceNotFoundException e) {
			throw new GameException(e);
		}
		if(team!=null) {
			team.setLocation(Location);
			teamrepo.save(team);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deletePlayerById(int playerId) throws GameException {
		Player player = null;
		try {
			player = playerrepo.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("player not found with id:"+playerId));	
		} catch (ResourceNotFoundException e) {
			throw new GameException(e);
		}
		if (player != null) {
			playerrepo.delete(player);
			return true;
		} else {
			return false;
		}
	}

}
