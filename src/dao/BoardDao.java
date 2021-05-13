package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.Board;


public class BoardDao {

	DataSource ds = null;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(Board board) throws Exception {
		Connection connection = null;
		int result = 0;
		
		PreparedStatement stmt = null;
		final String sqlInsert = "INSERT INTO post(movie_gen,title,post_content,writedate,writer,user_id) VALUES(?, ?, ?, NOW(),?, ?)";

		try {
			// 커넥션 풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlInsert);
			stmt.setString(1, board.getGenre());
			stmt.setString(2, board.getTitle());
			stmt.setString(3, board.getContent());
			stmt.setString(4, board.getWriter());
			stmt.setInt(5, board.getUserId());
			result = stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				//DataSource가 빌려준 Connection은 close()시 연결이 종료되는 것이 아니라 반납하는 의미이다
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Board> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = "SELECT post_id,title,post_content from post ORDER BY post_id ASC";

		try {
			// 커넥션 풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			ArrayList<Board> selectAllBoard = new ArrayList<Board>();

			while (rs.next()) {
				selectAllBoard.add(new Board().setNo(rs.getInt("post_id")).setTitle(rs.getString("title"))
						.setContent(rs.getString("post_content")));
			}

			return selectAllBoard;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(connection != null)
					connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 게시판의 전체 글 수를 가져오는 메서드
		public int getBoardCount() {
			String sql = "select count(*) from post";

			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int boardCount=0;
			try {
				connection = ds.getConnection();
				pstmt = connection.prepareStatement(sql); 
				rs = pstmt.executeQuery();
	
				if (rs.next()) {
					boardCount=rs.getInt(1); // 전체 글 개수
				} 

			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
				try {
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if(connection != null)
						connection.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return boardCount;
		}

		
		
		
		// 게시글 리스트 읽어오기 page=현재 페이지  pageSize=페이지당 글 개수
		public ArrayList<Board> selectAllBoards(int page, int pageSize) {

			int startNum=((page-1)*pageSize)+1;
			int endNum=page*pageSize;
			
			ArrayList<Board> lists = new ArrayList<Board>();
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "SELECT post_id,title,post_content from post "
					+ "WHERE post_id >=? and post_id <=? ORDER BY post_id DESC";
				
			try {
				connection = ds.getConnection();
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, startNum);
				pstmt.setInt(2, endNum);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Board b = new Board();
					b.setNo(rs.getInt("post_id"));
					b.setTitle(rs.getString("title"));
					b.setContent(rs.getString("post_content"));
					
					lists.add(b);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if(connection != null)
						connection.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return lists;
		}
	
	
	// 세션에 있는 유저의 데이터 베이스 정보를 빼내기	
	public Member selectOne(Member member) throws Exception {
	
	}
	
	public Member exist(String email, String password) throws Exception {
		
	}
	
	public int update(Member member) throws Exception {
	
	}
	
	public int delete(int no) throws Exception {
	
	}
}
