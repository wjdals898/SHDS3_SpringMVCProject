package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// DAO(Data Access Object)
@Repository
public class EmpDAOMybatis implements EmpDAOInterface {
	
	@Autowired
	SqlSession sqlSession;
	
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	String namespace = "com.shinhan.emp.";

	public EmpDTO loginCheck(String email, String phone) {
		System.out.println("email="+email);
		System.out.println("phone="+phone);
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("phone", phone);
		EmpDTO emp = sqlSession.selectOne(namespace+"loginCheck", map);
		logger.info(emp==null?"존재하지 않는 직원": emp.toString());
		return emp;
	}
	
	public List<JobDTO> selectAllJob() {
		List<JobDTO> joblist = sqlSession.selectList(namespace+"selectAllJob");
		logger.info(joblist.toString());
		return joblist;
	}
	
	public List<HashMap<String, Object>> selectAllManager() {
		List<HashMap<String, Object>> mgrlist = sqlSession.selectList(namespace+"selectAllManager");
		logger.info(mgrlist.toString());
		return mgrlist;
	}

	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectAll");
		logger.info(emplist.toString());
		return emplist;
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace+"selectById", empid);
		logger.info(emp!=null?emp.toString():"data없음");
		return emp;
	}
	
	public int selectByEmail(String email) {
		return sqlSession.selectOne(namespace+"selectByEmail", email);
	}

	public List<EmpDTO> selectByDepartmentId(int deptId) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectByDepartmentId", deptId);
		logger.info(emplist.toString());
		return emplist;
	}

	public List<EmpDTO> selectByJobId(String jobid) {
		List<EmpDTO> emplist =sqlSession.selectList(namespace+"selectByJobId", jobid);
		logger.info(emplist.toString());
		return emplist;
	}
	
	public List<EmpDTO> selectByJobId2(String jobid) {
		return null;
	}
	
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary){
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("deptid", deptid);
		conditionMap.put("jobid", jobid);
		conditionMap.put("hdate", hdate);
		conditionMap.put("salary", salary);
		logger.info("deptid:"+deptid);
		logger.info("jobid:"+jobid);
		logger.info("hdate:"+hdate);
		logger.info("salary:"+salary);
		List<EmpDTO> emplist = sqlSession.selectList(namespace+"selectByCondition", conditionMap);
		logger.info(emplist.toString());
		logger.info(emplist.size()+"건 조건 조회됨");
		return emplist;
	}

	public int empInsert(EmpDTO emp) {
		int result = sqlSession.insert(namespace+"empInsert", emp);
		logger.info(result+"건 입력됨");
		return result;
	}

	public int empUpdate(EmpDTO emp) {
		int result = sqlSession.update(namespace+"empUpdate", emp);
		logger.info(result+"건 수정됨");
		return result;
	}

	public int empDelete(int empid) {
		int result = sqlSession.delete(namespace+"empDelete", empid);
		logger.info(result+"건 삭제됨");
		return result;
	}
	
}
