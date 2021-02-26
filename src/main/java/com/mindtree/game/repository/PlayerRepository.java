package com.mindtree.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.game.entity.Player;
import com.mindtree.game.entity.Team;
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	public List<Player>getPlayerByTeam(Team team);

}
