package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import beans.Flashcard;
import util.ConnectionUtil;

public class FlashcardDAOImpl implements FlashcardDAO {
	private Logger log = Logger.getRootLogger();

	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	public long insertFlashcard(Flashcard flashcard) {
		log.trace("inserting new flashcard ");
		Connection conn = connUtil.getConnection();

		try {
			CallableStatement cs = conn.prepareCall("call betterinsertcard(?,?,?)");
			cs.setString(1, flashcard.getQuestion());
			cs.setString(2, flashcard.getAnswer());
			cs.registerOutParameter(3, java.sql.Types.BIGINT);
			cs.execute();
			return cs.getLong(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public boolean updateFlashcard(Flashcard flashcard) {
		// TODO Auto-generated method stub
		return false;
	}

	public Flashcard getFlashcardById(long id) {
		log.trace("getting flashcard with id " + id);
		Flashcard fc = null;
		Connection conn = connUtil.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement("select * from flashcard where flashcardid = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				fc = new Flashcard(rs.getLong("FLASHCARDID"), rs.getString("QUESTION"), rs.getString("ANSWER"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fc;
	}

	public List<Flashcard> getAllFlashcards() {
		log.trace("getting all flashcards");
		List<Flashcard> fcList = new ArrayList<Flashcard>();
		Connection conn = connUtil.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement("select * from flashcard");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				fcList.add(new Flashcard(rs.getLong("FLASHCARDID"), rs.getString("QUESTION"), rs.getString("ANSWER")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return fcList;
	}

}
