package study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import study.model.CricketTeam;

public interface CricketTeamRepository extends JpaRepository<CricketTeam, Integer> {

	public Optional<CricketTeam> findById (int id);
}
