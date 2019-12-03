package com.lys.usermanager.repository;

import com.lys.usermanager.entity.SysStation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 单位Repository
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface StationRepository extends JpaRepository<SysStation, Long> {
}
