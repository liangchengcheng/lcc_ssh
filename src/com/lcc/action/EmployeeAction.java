package com.lcc.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import java.util.Map;
import com.lcc.entity.Employee;
import com.lcc.service.DepartmentService;
import com.lcc.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,Preparable {

	private static final long serialVersionUID = 1L;

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	private DepartmentService departmentService;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	private Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}
	
	public String list() {
		request.put("employees", employeeService.getAll());
		return "list";
	}

	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String delete() {
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
