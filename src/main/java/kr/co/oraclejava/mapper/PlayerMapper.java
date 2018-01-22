package kr.co.oraclejava.mapper;

import java.util.List;

import kr.co.oraclejava.service.Player;

public interface PlayerMapper {
	public List<Player> selectList();

	public Player select(int playerId);

	public void insert(Player player);

	public void update(Player player);

	public void delete(Player player);
}
