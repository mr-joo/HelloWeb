package kr.co.oraclejava.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.co.oraclejava.mapper.PlayerMapper;

@Component("playerDaoMyBatis2")
public class PlayerDaoMyBatis2 implements PlayerDao {

	@Autowired
	private PlatformTransactionManager txManager;

	@Autowired
	private PlayerMapper playerMapper;

	@Override
	public List<Player> getPlayerList() {
		return playerMapper.selectList();
	}

	@Override
	public Player getPlayer(int playerId) {
		return playerMapper.select(playerId);
	}

	@Override
	public void insertPlayer(Player player) {
		//트랜젝션 개시
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus txStatus = txManager.getTransaction(txDefinition);
		try {
			playerMapper.insert(player);
			int i = 1;
			if (i == 2) {
				throw new RuntimeException("Exception!");
			}
			txManager.commit(txStatus);
		} catch(Exception e) {
			txManager.rollback(txStatus);
			throw e;
		}
	}

	@Override
	@Transactional
	public void updatePlayer(Player player) {
		playerMapper.update(player);
		int i = 1;
		if (i == 2) {
			throw new RuntimeException("Exception!");
		}
	}

	@Override
	public void deletePlayer(Player player) {
		playerMapper.delete(player);
	}

}
