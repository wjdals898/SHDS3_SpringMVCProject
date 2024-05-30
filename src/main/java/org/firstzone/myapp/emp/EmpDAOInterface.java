package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface EmpDAOInterface {

	public EmpDTO loginCheck(String email, String phone);
	
	public List<JobDTO> selectAllJob();
	
	public List<HashMap<String, Object>> selectAllManager();
	
	public List<EmpDTO> selectAll();

	public EmpDTO selectById(int empid);
	
	public int selectByEmail(String email);

	public List<EmpDTO> selectByDepartmentId(int deptId);

	public List<EmpDTO> selectByJobId(String jobid);
	
	public List<EmpDTO> selectByJobId2(String jobid);
	
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary);

	public int empInsert(EmpDTO emp);

	public int empUpdate(EmpDTO emp);

	public int empDelete(int empid);
}
