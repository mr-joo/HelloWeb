package kr.co.oraclejava.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

//@Component("PlayerDaoJdbc")
public class PlayerDaoJdbc implements PlayerDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//��ü ���� ���
	public List<Player> getPlayerList() {
		String sql = "select * from player";
		//List<Player> list = this.jdbcTemplate().query(sql, new RowMapper()
		//jdbcTemplate.query(sql, new RowMapper() ������ ���� id �Է��ϸ� ���� �߻�
		List<Player> list = jdbcTemplate.query(sql, new RowMapper() {
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				Player player = new Player();
				player.setId(rs.getString("id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getString("age"));
				player.setSalary(rs.getString("salary"));
				player.setFileName(rs.getString("fileName"));
				return player;
			}
		});
		return list;
	}
	
	//�ش� ID�� ���� ����
	public Player getPlayer(int playerid) {
		String sql = "select * from player where id = ?";
		Player player = null;
		
		player = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Player>(Player.class), playerid);
		
		return player;
	}
	
	//���� ���̺� ���� �߰�
	public void insertPlayer(Player player) {
		String sql = "insert into player(name, age, salary, filename) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, player.getName(), player.getAge(), player.getSalary(), player.getFileName());
	}
	
	//���� ���̺� ���� ����
	public void updatePlayer(Player player) {
		String sql = "update player set name=?, age=?, salary=?, ";
				sql += (player.getFileName().isEmpty())? "" : ", filename=?"; 
				sql += "wher id=?";
				if(player.getFileName().isEmpty()) {
					jdbcTemplate.update(sql, player.getName(),
					player.getAge(),
					player.getSalary(),
					player.getId());
					
				} else {
					jdbcTemplate.update(sql, player.getName(),
					player.getAge(),
					player.getSalary(),
					player.getFileName(),
					player.getId());
				}
		jdbcTemplate.update(sql, player.getName(), player.getAge(), player.getSalary(), player.getFileName(), player.getId());
	}
	
	//���� ���̺� ���� ����
	public void deletePlayer(Player player) {
		String sql = "delete from player " + "where id=?";
		jdbcTemplate.update(sql, player.getId());
	}
}

