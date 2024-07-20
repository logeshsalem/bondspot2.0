package com.application.service;

import java.time.LocalDateTime;
import java.util.List;
import com.application.entity.Match;

public interface MatchService {
	
	List<String> getAllMatches();
	
	Match getMatchById(int id);	
	
	List<Match> findByStatus(String status);
	
	void delete(int id);
	
	Match save(int userId1, int userId2, String status, LocalDateTime matchDate);
	
	List<Match> findMatchByUserId(int userId);

	

	

}
