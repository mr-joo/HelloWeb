package kr.co.oraclejava.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("playerDaoMyBatis")
public class PlayerDaoMyBatis implements PlayerDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Player> getPlayerList() {
		return sqlSession.selectList("player.selectList");
	}

	@Override
	public Player getPlayer(int playerId) {
		return sqlSession.selectOne("player.select", playerId);
	}

	@Override
	public void insertPlayer(Player player) {
		sqlSession.insert("player.insert", player);
	}

	@Override
	public void updatePlayer(Player player) {
		sqlSession.update("player.update", player);
	}

	@Override
	public void deletePlayer(Player player) {
		sqlSession.delete("player.delete", player);
	}
	
}
