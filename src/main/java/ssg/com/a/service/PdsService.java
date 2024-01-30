package ssg.com.a.service;

import java.util.List;

import ssg.com.a.dto.PdsDto;

public interface PdsService {
	List<PdsDto> pdslist();
	boolean pdsupload(PdsDto dto);
	PdsDto getPds(int seq);
	
	void pdsdowncount(int seq);
}
