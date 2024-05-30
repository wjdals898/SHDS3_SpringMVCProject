package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Controller -> Service -> DAO
//            <-         <-
// Service
@Service
public class EmpService {

	@Autowired 	// type이 같으면 자동으로 Injection
	EmpDAOInterface empDAO;
	
	public EmpDTO loginCheck(String email, String phone) {
		
		return empDAO.loginCheck(email, phone);
	}
	
	public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}
	
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	public List<EmpDTO> selectAll() {
		return empDAO.selectAll();
	}

	public EmpDTO selectById(int empid) {
		return empDAO.selectById(empid);
	}
	
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	public List<EmpDTO> selectByDepartmentId(int deptId) {
		return empDAO.selectByDepartmentId(deptId);
	}

	public List<EmpDTO> selectByJobId(String jobid) {
		return empDAO.selectByJobId(jobid);
	}

	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	/*
	 * public Map<String, Object> empInfo(int empid) { return empDAO.empInfo(empid);
	 * }
	 * 
	 * public double callFunction(int empid) { return empDAO.callFunction(empid); }
	 */

}
