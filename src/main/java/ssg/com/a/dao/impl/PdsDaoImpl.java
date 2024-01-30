package ssg.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;

@Repository
public class PdsDaoImpl implements PdsDao {
	
	@Autowired
	//SqlSession은 ibatis SqlSessionTemplate은 mybatis
	SqlSessionTemplate session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> pdslist() {
		return session.selectList(ns + "pdslist");
	}

	@Override
	public int pdsupload(PdsDto dto) {
		return session.insert(ns + "pdsupload", dto);
	}

	@Override
	public PdsDto getPds(int seq) {
		return session.selectOne(ns + "getPds", seq);
	}

	@Override
	public void pdsreadcount(int seq) {
		session.update(ns + "pdsreadcount", seq);
	}

	@Override
	public void pdsdowncount(int seq) {
		session.update(ns + "pdsdowncount", seq);
	}
}
