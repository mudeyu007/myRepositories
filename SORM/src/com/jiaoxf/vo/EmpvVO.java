package com.jiaoxf.vo;
/**
 * 	多表联合查询时，用于封装查询信息（要查询的字段/别名）
 * 
 * @author acer
 *
 */
public class EmpvVO {
	//select eid,e.empname,salary+bonus 'xinshui',age,d.dname 'deptName',
	//d.address 'deptAddr' from emp e join dept d on e.deptId=D.id;
	
	private Integer id;
	private String empname;
	private Double xinshui;
	private Integer age;
	private String deptName;
	private String deptAddr;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Double getXinshui() {
		return xinshui;
	}
	public void setXinshui(Double xinshui) {
		this.xinshui = xinshui;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptAddr() {
		return deptAddr;
	}
	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}
	
	public EmpvVO() {
		
	}
	
	
}











