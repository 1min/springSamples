package ssg.com.a.dao;

import java.util.List;

import ssg.com.a.dto.PdsDto;

public interface PdsDao {
	List<PdsDto> pdslist();
	
	int pdsupload(PdsDto dto);
	
	PdsDto getPds(int seq);
	
	void pdsreadcount(int seq);
	void pdsdowncount(int seq);
}
