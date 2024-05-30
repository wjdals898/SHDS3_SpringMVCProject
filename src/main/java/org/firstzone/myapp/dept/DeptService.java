package org.firstzone.myapp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
	
	@Autowired
	@Qualifier("deptmybatis")
	DeptDAOInterface deptDao;

	public List<DeptDTO> selectAll() {
		return deptDao.selectAll();
	}

	public DeptDTO selectById(int deptid) {
		return deptDao.selectById(deptid);
	}

	public List<DeptDTO> selectByName(String deptname) {
		return deptDao.selectByName(deptname);
	}

	public int deptInsert(DeptDTO dept) {
		return deptDao.deptInsert(dept);
	}

	public int deptUpdate(DeptDTO dept) {
		return deptDao.deptUpdate(dept);
	}

	public int deptDelete(int deptid) {
		return deptDao.deptDelete(deptid);
	}
}
