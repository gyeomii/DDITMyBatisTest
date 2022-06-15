package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.vo.BoardVO;
import util.MyBatisUtilGyeom;


public class BoardDaoImpl implements IBoardDao {

	private static IBoardDao boardDao;
	private SqlSession sqlSession;
	private BoardDaoImpl() {
		sqlSession = MyBatisUtilGyeom.getInstance();
	}

	public static IBoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public List<BoardVO> getAllPostList() {
		List<BoardVO> boardList = sqlSession.selectList("board.getPostAll");
		return boardList;
	}

	@Override
	public int writePost(BoardVO bv) {
		int cnt = sqlSession.insert("board.writePost",bv);
		
		if(cnt > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		return cnt;
	}

	@Override
	public int deletePost(int boardNo) {
		int cnt = sqlSession.delete("board.deletePost", boardNo);
		
		if(cnt > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		return cnt;
	}

	@Override
	public int editPost(BoardVO bv) {
		int cnt = sqlSession.update("board.editPost",bv);
		
		if(cnt > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		List<BoardVO> boardList = sqlSession.selectList("board.searchPost",bv);
		
		return boardList;
	}

	@Override
	public boolean checkBoard(int boardNo) {
		boolean chk = false;
		int cnt = (int) sqlSession.selectOne("board.checkPost",boardNo);
		if(cnt > 0) {
			chk = true;
		}
		
		return chk;
	}

}
