package com.lys.usermanager.repository;

import com.lys.usermanager.entity.SysDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 部门Repository
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface DepartmentRepository extends JpaRepository<SysDepartment, Long> {
}
