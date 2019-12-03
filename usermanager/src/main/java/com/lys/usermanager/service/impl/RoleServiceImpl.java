package com.lys.usermanager.service.impl;

import com.lys.usermanager.config.CacheConfigAll;
import com.lys.usermanager.entity.SysRole;
import com.lys.usermanager.entity.SysUserRow;
import com.lys.usermanager.repository.RoleRepository;
import com.lys.usermanager.repository.UserRowRepository;
import com.lys.usermanager.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @description: 角色
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-09 13:41
 **/
@Service
@CacheConfig(cacheNames = "RoleService", cacheManager = CacheConfigAll.LAYERING_CACHE_MANAGER)
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRowRepository userRowRepository;

    @CacheEvict(key = "#sysRole.fId")
    @Override
    public SysRole saveRole(SysRole sysRole) {
        return roleRepository.save(sysRole);
    }

    @Override
    public List<SysRole> findRolesByUserId(String userId) {
        List<SysUserRow> byFUserId = userRowRepository.findByfUserId(userId);
        List<SysRole> sysRoles = new ArrayList<>(byFUserId.size());
        byFUserId.forEach(sysUserRow -> {
            RoleServiceImpl bean = applicationContext.getBean(RoleServiceImpl.class);
            Optional<SysRole> byId = bean.findById(sysUserRow.getfRoleId());
            if (byId.isPresent()) {
                sysRoles.add(byId.get());
            }
        });
        return sysRoles;
    }

    @CacheEvict
    @Override
    public void deleteRoles(Set<String> roleIds) {
        Set<SysRole> collect = roleIds.stream()
                .map(id -> roleRepository.getOne(id))
                .collect(Collectors.toSet());
        roleRepository.deleteInBatch(collect);
    }


    @Cacheable(key = "#id", unless = "#result == null")
    @Override
    public Optional<SysRole> findById(String id) {
        return roleRepository.findById(id);
    }


}
