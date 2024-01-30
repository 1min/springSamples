package ssg.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao{
	
	@Autowired
	SqlSession session;
	
	// mapper namepspace
	String ns = "Bbs.";

	@Override
	public List<BbsDto> bbslist(BbsParam param) {
		return session.selectList(ns + "bbslist", param);
	}

	@Override
	public int allBbs(BbsParam param) {
		return session.selectOne(ns + "allbbs", param);
	}

	@Override
	public int bbswrite(BbsDto dto) {
		int count = session.insert(ns + "bbswrite", dto);
		
		return count;
	}

	@Override
	public BbsDto bbsdetail(int seq) {
		return session.selectOne(ns + "bbsdetail", seq);
	}

	@Override
	public int commentWrite(BbsComment com) {
		return session.insert(ns + "commentWrite", com);
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return session.selectList(ns + "commentList", seq);
	}

	@Override
	public int bbsreadcount(int seq) {
		
		return session.update(ns + "bbsreadcount", seq);
	}

	@Override
	public void BbsAnswerUpdate(BbsDto dto) {
		session.update(ns + "BbsAnswerUpdate", dto);
	}

	@Override
	public int BbsAnswerInsert(BbsDto dto) {
		return session.insert(ns + "BbsAnswerInsert", dto);
	}

	@Override
	public int bbsupdate(BbsDto dto) {
		return session.update(ns + "bbsupdate", dto);
	}

	@Override
	public int bbsdelete(int seq) {
		return session.update(ns + "bbsdelete", seq);
	}
	
}
