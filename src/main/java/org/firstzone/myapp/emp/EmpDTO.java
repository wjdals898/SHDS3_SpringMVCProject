package org.firstzone.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// VO(Value Object)
// DTO(Data Transfer Object)
// JavaBeans 湲곗?���뿉�꽌 �씠�슜(Jsp, Spring, Mybatis�뒗 javabeans 湲곗?���쓣 �씠�슜�븳�떎.)
//@AllArgsConstructor
//@NoArgsConstructor // �븘�닔
@Getter@Setter // �븘�닔
@ToString
public class EmpDTO {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private Integer salary;
	private Double commission_pct;
	private Integer manager_id;
	private Integer department_id;
}
