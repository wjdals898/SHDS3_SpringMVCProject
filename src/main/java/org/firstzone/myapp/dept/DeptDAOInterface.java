package org.firstzone.myapp.dept;

import java.util.List;

public interface DeptDAOInterface {

	public List<DeptDTO> selectAll();

	public DeptDTO selectById(int deptid);
	
	public List<DeptDTO> selectByName(String deptname);

	public int deptInsert(DeptDTO dept);

	public int deptUpdate(DeptDTO dept);

	public int deptDelete(int deptid);
}
