package org.firstzone.myapp.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.util.DBUtil;

@Repository
public class DeptDAO implements DeptDAOInterface {
	
	// ≈∏¿‘¿Ã ∞∞¿∏∏È Injection
	// ∞∞¿∫ ≈∏¿‘¿Ã ø©∑Ø∞≥¿Ã∏È Error
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
	Connection conn;
	Statement st; // Î∞îÏù∏?î©Î≥??àò Ïß??õê?ïòÏß? ?ïä?ùå
	PreparedStatement pst; // StatementÎ•? ?ÉÅ?ÜçÎ∞õÏùå, Î∞îÏù∏?î©Î≥??àò Ïß??õê
	ResultSet rs;

	// 1.Î∂??Ñú Î™®Îëê Ï°∞Ìöå
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * from departments";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}

		return deptlist;
	}

	// 2.?äπ?†ï Î∂??Ñú?ùò ?ÉÅ?Ñ∏Î≥¥Í∏∞
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		String sql = "select * from departments where department_id = ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			rs = pst.executeQuery();
			if (rs.next()) {
				dept = makeDept(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return dept;
	}
	
	// Î∂??Ñú?ù¥Î¶ÑÏúºÎ°? Ï°∞Ìöå(???ÜåÎ¨∏Ïûê ?ÉÅÍ¥??óÜ?ùå, ?ùºÎ∂??ûÖ?†•?úºÎ°? Ï°∞ÌöåÍ∞??ä•)
	public List<DeptDTO> selectByName(String deptname) {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * from departments where lower(department_name) like lower('%'||?||'%')";
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, deptname);
			rs = pst.executeQuery();
			while(rs.next()) {
				DeptDTO dept = makeDept(rs);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deptlist;
	}

	// 3. ?ûÖ?†•
	public int deptInsert(DeptDTO dept) {
		int result = 0;
		String sql = "insert into departments values(?,?,?,?)";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dept.getDepartment_id());
			pst.setString(2, dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getLocation_id());
			result = pst.executeUpdate(); // DMLÎ¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? executeQuery

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}

	// 7. ?àò?†ï(Update)
	public int deptUpdate(DeptDTO dept) {
		int result = 0;
		String sql = "update departments" + " set" + "    department_name = ?," + "    manager_id=?,"
				+ "    location_id=?" + "where department_id = ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getManager_id());
			pst.setInt(3, dept.getLocation_id());
			pst.setInt(4, dept.getDepartment_id());
			result = pst.executeUpdate(); // DMLÎ¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? executeQuery
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return result;
	}

	// 8. ?Ç≠?†ú(Delete)
	public int deptDelete(int deptid) {
		int result = 0;
		String sql = "delete from departments where department_id=?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			result = pst.executeUpdate(); // DMLÎ¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? executeQuery
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return result;
	}

	private DeptDTO makeDept(ResultSet rs) throws SQLException {
		DeptDTO dept = new DeptDTO();

		dept.setDepartment_id(rs.getInt("department_id"));
		dept.setDepartment_name(rs.getString("department_name"));
		dept.setLocation_id(rs.getInt("location_id"));
		dept.setManager_id(rs.getInt("manager_id"));

		return dept;
	}
}
