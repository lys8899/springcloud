package com.lys.usermanager.service.impl;

import com.google.common.base.Strings;
import com.lys.usermanager.entity.SysFunction;
import com.lys.usermanager.entity.SysRole;
import com.lys.usermanager.entity.SysUser;
import com.lys.usermanager.entity.SysUserRow;
import com.lys.usermanager.repository.UserRepository;
import com.lys.usermanager.repository.UserRowRepository;
import com.lys.usermanager.service.FunctionService;
import com.lys.usermanager.service.RoleService;
import com.lys.usermanager.service.UserService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 用户
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:08
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository repository;
    @Autowired
    UserRowRepository userRowRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public SysUser addUser(SysUser sysUser) {
        String password = sysUser.getPassword();
        if (!Strings.isNullOrEmpty(password)) {
            password = new BCryptPasswordEncoder().encode(password);
            sysUser.setfPassword(password);
        }
        return repository.save(sysUser);
    }

    public String saveUserForEs(SysUser sysUser) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(sysUser.getfId())
                .withObject(sysUser)
                .build();
        String index = elasticsearchTemplate.index(indexQuery);

        return index;
    }

    @Override
    public void deleteUsers(Set<String> userIds) {
        Set<SysUser> collect = userIds.stream()
                .map(id -> repository.getOne(id))
                .collect(Collectors.toSet());
        repository.deleteInBatch(collect);
    }


    @Override
    public Optional<SysUser> getUserById(String id) {
        Optional<SysUser> byId = repository.findById(id);
        return byId;
    }


    @Override
    public Optional<SysUser> getUserByIdAll(String id) {
        Optional<SysUser> byId = getUserById(id);
        addRoleAndFunction(byId);
        return byId;
    }

    private void addRoleAndFunction(Optional<SysUser> optionalUser) {
        if (optionalUser.isPresent()) {
            SysUser sysUser = optionalUser.get();
            List<SysRole> rolesByUserId = roleService.findRolesByUserId(sysUser.getfId());
            sysUser.setSysRoles(rolesByUserId);
            if (!rolesByUserId.isEmpty()) {
                Set<String> stringSet = rolesByUserId.stream().map(sysRole -> sysRole.getfId()).collect(Collectors.toSet());
                List<SysFunction> functionsByRoleId = functionService.findFunctionsByRoleIds(stringSet);
                sysUser.setSysFunctions(functionsByRoleId);
            }
        }
    }


    @Override
    public Page<SysUser> listTable(String name, Pageable pageable) {
        Specification<SysUser> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!Strings.isNullOrEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("fName").as(String.class), name));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        return repository.findAll(specification, pageable);
    }


    public Page<SysUser> listTableForEs(String name, Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(name))
                .withPageable(pageable)
                .build();
        AggregatedPage<SysUser> sysUsers = elasticsearchTemplate.queryForPage(searchQuery, SysUser.class);
        return sysUsers;
    }


    @Transactional(rollbackFor = RuntimeException.class, isolation = Isolation.SERIALIZABLE)
    @Override
    public int changePassword(String userId, String oldPassword, String newPassword, Long version) {
        Optional<SysUser> optionalUser = repository.findById(userId);

        if (!optionalUser.isPresent()) {
            return 2;
        }
        SysUser sysUser = optionalUser.get();
        entityManager.detach(sysUser);
        sysUser.setVersion(version);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(oldPassword, sysUser.getPassword())) {
            return 3;
        }
        newPassword = encoder.encode(newPassword);
        sysUser.setfPassword(newPassword);

        repository.save(sysUser);

        return 1;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void bindUserAndRoles(String userId, String[] roleIds) {
        List<SysUserRow> byFUserId = userRowRepository.findByfUserId(userId);
        userRowRepository.deleteInBatch(byFUserId);

        for (String roleId : roleIds) {
            SysUserRow sysUserRow = new SysUserRow();
            sysUserRow.setfRoleId(roleId);
            sysUserRow.setfUserId(userId);
            userRowRepository.save(sysUserRow);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<SysUser> byAccount = repository.findByfAccount(account);
        addRoleAndFunction(byAccount);
        return byAccount.get();
    }


}
