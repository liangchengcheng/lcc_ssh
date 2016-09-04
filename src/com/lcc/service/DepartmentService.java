package com.lcc.service;

import java.util.List;
import com.lcc.dao.DepartmentDao;
import com.lcc.entity.Department;

public class DepartmentService {
	private DepartmentDao departmentDao;

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> getAll() {
		return departmentDao.getAll();
	}
}
