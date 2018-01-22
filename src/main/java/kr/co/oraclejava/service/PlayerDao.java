package kr.co.oraclejava.service;

import java.util.List;

public interface PlayerDao{
	public List<Player> getPlayerList();
	
	public Player getPlayer(int playerId);
	
	public void insertPlayer(Player player);
	public void updatePlayer(Player player);
	public void deletePlayer(Player player);
}
