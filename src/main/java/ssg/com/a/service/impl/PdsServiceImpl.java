package ssg.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {
	
	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> pdslist() {
		return dao.pdslist();
	}

	@Override
	public boolean pdsupload(PdsDto dto) {
		int count = dao.pdsupload(dto);
		return count > 0;
	}

	@Override
	public PdsDto getPds(int seq) {
		dao.pdsreadcount(seq);
		
		return dao.getPds(seq);
	}

	@Override
	public void pdsdowncount(int seq) {
		dao.pdsdowncount(seq);
	}
}
