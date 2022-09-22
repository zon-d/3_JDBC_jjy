package edu.kh.jdbc.board.model.vo;

import java.util.List;

public class Comment {
	
	private int commentNo;			// 댓글 번호
	private String commentContent;	// 댓글 내용
	private int memberNo;			// 작성자 회원 번호
	private String memberName;		// 작성자 회원 이름
	private String createDate;		// 작성자 회원 이름
	private int boardNo;
	
	private List<Comment> commentList;

	public Comment(int commentNo, String commentContent, int memberNo, String memberName, String createDate,
			int boardNo, List<Comment> commentList) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.createDate = createDate;
		this.boardNo = boardNo;
		this.commentList = commentList;
	}

	public Comment() {}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	

}
