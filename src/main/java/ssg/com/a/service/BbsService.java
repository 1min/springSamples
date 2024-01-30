package ssg.com.a.service;

import java.util.List;

import ssg.com.a.dto.BbsComment;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

public interface BbsService {
	List<BbsDto> bbslist(BbsParam param);
	int allBbs(BbsParam param);
	boolean bbswrite(BbsDto dto);
	BbsDto bbsdetail(int seq);
	
	boolean commentWrite(BbsComment com);
	List<BbsComment> commentList(int seq);
	
	boolean BbsAnswer(BbsDto dto);
	
	boolean bbsupdate(BbsDto dto);
	boolean bbsdelete(int seq);
}
