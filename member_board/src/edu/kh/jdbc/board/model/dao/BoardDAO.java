package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;

public class BoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception {
		// 결과 저장용 변수 선언
		List<Board> boardList = new ArrayList<>();
		
		
		try {
			// SQL ~
			String sql = prop.getProperty("selectAllBoard");
			
			// Statement ~
			stmt = conn.createStatement();
			
			// ResultSet에 저장된 값을 List 옮겨 담기
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				boardList.add(board);

			}
			
		}finally {
			// ~~
			close(rs);
			close(stmt);
		}
		
		
		// 조회 결과 반환
		return boardList;
	}

	/** 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception {
		// 결과 저장용 변수 선언
		Board board = null;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("selectBoard");
			
			// Preparedstatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setInt(1, boardNo);
			
			// SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 조회 결과가 있을 경우
				board = new Board(); // Board 객체 생성 == board는 null 아님
				
				board.setBoardNo(		rs.getInt	("BOARD_NO"));
				board.setBoardTitle(	rs.getString("BOARD_TITLE"));
				board.setBoardContent(	rs.getString("BOARD_CONTENT"));
				board.setMemberName(	rs.getString("MEMBER_NM"));
				board.setReadCount(		rs.getInt	("READ_COUNT"));
				board.setCreateDate(	rs.getString("CREATE_DT"));
				board.setMemberNo( rs.getInt("MEMBER_NO"));
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board; // 조회 결과
	}

	/** 조회 수 증가
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("increaseReadCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  boardNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return result;
	}

	/** 게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 삭제
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception{
	
		int result = 0;

		try {
			String sql = prop.getProperty("deleteBoard");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}
	
	

}
