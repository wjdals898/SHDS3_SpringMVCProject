package org.firstzone.myapp.dept;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deptmybatis")
public class DeptDAOMybatis implements DeptDAOInterface {
	
	// 타입이 같으면 Injection
	// 같은 타입이 여러개이면 Error
	@Autowired
	SqlSession sqlSession;
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	String namespace = "com.shinhan.dept.";
	
	public List<DeptDTO> selectAll() {
		logger.info("DeptDAOMybatis....selectAll()");
		return sqlSession.selectList(namespace+"selectAll");
	}

	public DeptDTO selectById(int deptid) {
		logger.info("DeptDAOMybatis....selectById()");
		return sqlSession.selectOne(namespace+"selectById", deptid);
	}
	
	public List<DeptDTO> selectByName(String deptname) {
		logger.info("DeptDAOMybatis....selectByName()");
		return sqlSession.selectList(namespace+"selectByName", deptname);
	}

	public int deptInsert(DeptDTO dept) {
		logger.info("DeptDAOMybatis....deptInsert()");
		return sqlSession.insert(namespace+"deptInsert", dept);
	}

	public int deptUpdate(DeptDTO dept) {
		logger.info("DeptDAOMybatis....deptUpdate()");
		return sqlSession.update(namespace+"deptUpdate", dept);
	}

	public int deptDelete(int deptid) {
		logger.info("DeptDAOMybatis....deptDelete()");
		return sqlSession.delete(namespace+"deptDelete", deptid);
	}

}
