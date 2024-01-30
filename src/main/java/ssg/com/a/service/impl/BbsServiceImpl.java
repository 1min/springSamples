package ssg.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> bbslist(BbsParam param) {
		return dao.bbslist(param);
	}

	@Override
	public int allBbs(BbsParam param) {
		return dao.allBbs(param);
	}

	@Override
	public boolean bbswrite(BbsDto dto) {
		return dao.bbswrite(dto) > 0;
	}

	@Override
	public BbsDto bbsdetail(int seq) {
		dao.bbsreadcount(seq);
		
		return dao.bbsdetail(seq);
	}

	@Override
	public boolean commentWrite(BbsComment com) {
		return dao.commentWrite(com) > 0;
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return dao.commentList(seq);
	}

	@Override
	public boolean BbsAnswer(BbsDto dto) {
		dao.BbsAnswerUpdate(dto);
		return dao.BbsAnswerInsert(dto) > 0;
	}
	
	@Override
	public boolean bbsupdate(BbsDto dto) {
		int count = dao.bbsupdate(dto);
		return count>0?true:false;
	}

	@Override
	public boolean bbsdelete(int seq) {	
		int count = dao.bbsdelete(seq);
		return count>0?true:false;
	}
}
