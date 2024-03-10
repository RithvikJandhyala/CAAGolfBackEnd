package com.caa.spring.mongo.api.service;
import java.util.Comparator;
import java.util.HashMap;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caa.spring.mongo.api.model.Match;
import com.caa.spring.mongo.api.model.Player;
import com.caa.spring.mongo.api.repository.PlayerRepository;
import com.caa.spring.mongo.api.repository.SchoolRepository;
@Service
public class PlayerService {
	@Autowired
	private PlayerRepository repository;
	@Autowired
	private SchoolRepository schoolRepository;

	public String savePlayer(Player player) {
		int id = generateID(player);
		player.setPlayerID(id);
		repository.save(player);
		return "Added " + player.getName()+" With ID " + player.getPlayerID();
	}
	
	private int generateID(Player player) {
		
		int id = 1;
		
		Map<String, Integer> divisionMap = new HashMap<>();
		divisionMap.put("JH", 0);
		divisionMap.put("HS", 500);
		String division = player.getDivision();
		String school = player.getSchool();
		
		List <Player> players = repository.findBySchoolAndDivision(school, division);
		if(players.size() == 0) {
			id+= schoolRepository.findByName(school).getID();
			id+= divisionMap.get(division);
		}
		else {
			Player maxIDPlayer = players.stream()
	                .max(Comparator.comparingInt(Player::getPlayerID))
	                .get();
			int maxID = maxIDPlayer.getPlayerID();
			id = maxID +1;
		}		
		return id;
	}
	

	public List<Player> getPlayers(){
		//return repository.findAll(Sort.by(Sort.Direction.DESC, "wins"));
		return repository.findAll(Sort.by(Sort.Direction.ASC, "playerID"));
	}
	
	public String resetPlayerScores(){
		//return repository.findAll(Sort.by(Sort.Direction.DESC, "wins"));
		List<Player> players = repository.findAll();
		Player player;
		for(int i = 0; i < players.size(); i++)
		{
			player = players.get(i);
			player.setRank(0);
			player.setWins(0);
			player.setLosses(0);
			player.setTies(0);
			player.setPointsWon(0);
		}
		repository.saveAll(players);
		return "Updated";
		
	}
	public List<Player> getPlayersByDivision(String division){
		if(division.equals("All Divisions")){
			return getPlayers();
		}
		else {
			List<Player> playersByDivision = repository.findByDivision(division);
			playersByDivision.sort(Comparator.comparing(Player	::getPlayerID));
			return playersByDivision;
		}
	}		
	public List<Player> getPlayersBySchoolAndDivision(String school, String division){
		return repository.findBySchoolAndDivision(school, division);
	}
	

	public Optional<Player> getPlayer(int id){
		return repository.findById(id);
	}
	
	public String deletePlayer(int id) {
		repository.deleteById(id);
		System.out.print("delete " + id);
		return "Player deleted with  " + id;
	}

	public String updateScores(List<Match> matches) {
		List<Player> players= repository.findAll();	
		for (Match match : matches) 
		{
			Player p1 = findPlayer(match.getPlayer1ID(),players);
			Player p2 = findPlayer(match.getPlayer2ID(),players);
			if(p1 == null && p2 == null) {
				System.out.println( "Match not valid");
			}
			else if(p1 == null && p2 != null) {
				p2.setWins(p2.getWins() + 1);
				p2.setPointsWon(p2.getPointsWon()+match.getPlayer2Score());
			}		
			else if(p1 != null && p2 == null) {
				System.out.println( "Match not valid");
				p1.setWins(p1.getWins() + 1);
				p1.setPointsWon(p1.getPointsWon()+match.getPlayer1Score());
			}		
			else if(match.getPlayer1Score()> match.getPlayer2Score())
			{
				p1.setWins(p1.getWins() + 1);
				p2.setLosses(p2.getLosses() + 1);
				p1.setPointsWon(p1.getPointsWon()+match.getPlayer1Score());
				p2.setPointsWon(p2.getPointsWon()+match.getPlayer2Score());
				
			}
			else if(match.getPlayer1Score()< match.getPlayer2Score()){
				p2.setWins(p2.getWins() + 1);
				p1.setLosses(p1.getLosses() + 1);
				p1.setPointsWon(p1.getPointsWon()+match.getPlayer1Score());
				p2.setPointsWon(p2.getPointsWon()+match.getPlayer2Score());
			}
			else {
				p1.setTies(p1.getTies()+1);
				p2.setTies(p2.getTies()+1);
				p1.setPointsWon(p1.getPointsWon()+match.getPlayer1Score());
				p2.setPointsWon(p2.getPointsWon()+match.getPlayer2Score());
			}
			
			
			
		}
		repository.saveAll(players);
		return "Player scores updated";
	}
	
	public Player findPlayer(int playerID, List<Player> players)
	{
		Player player;
		for(int i = 0; i < players.size(); i++)
		{
			player = players.get(i);
			if(player.getPlayerID() == playerID) {
				return player;
			}
		}
		return null;
	}
	public List<Player> getPlayersBySchool(String school, Sort sort){
		return repository.findBySchool(school,sort);
	}
	public String clear() {
		repository.deleteAll();
		return "ALL PLAYERS CLEARED";
	}
	public String createGhostPlayers(String school,int id){
		Player ghostJH = new Player(id, "Ghost" + " JH " + school, school, "JH",  0, 0, 0, 0,0);
		Player ghostHS = new Player(id+500, "Ghost" + " HS " + school, school, "HS",  0, 0, 0, 0,0);
		repository.save(ghostJH);
		repository.save(ghostHS);
		return "created ghost players";
		
	}
		
}
