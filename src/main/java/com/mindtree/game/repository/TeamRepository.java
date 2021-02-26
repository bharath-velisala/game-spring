package com.mindtree.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.game.entity.Team;
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
	public Team getTeamByTeamName(String teamName);
	

}
