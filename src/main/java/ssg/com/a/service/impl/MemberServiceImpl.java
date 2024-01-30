package ssg.com.a.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.MemberDao;
import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Override
	public boolean idcheck(String id) {
		int count = dao.idcheck(id);
		
		return count > 0;
	}

	@Override
	public boolean addmember(MemberDto mem) {
		int count = dao.addmember(mem);
		
		return count > 0;
	}

	@Override
	public MemberDto login(MemberDto mem) {
		return dao.login(mem);
	}
}
