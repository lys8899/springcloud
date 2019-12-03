package com.lys.usermanager.entity;

import com.lys.usermanager.common.EntityParent;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


/**
 * @description: 角色
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 12:42
 **/
@Entity
@Table(name = "sys_role")
public class SysRole extends EntityParent {

    private String fName;
    private String fDescrib;
    @Transient
    private List<SysFunction> sysFunctions = new ArrayList<>();

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfDescrib() {
        return fDescrib;
    }

    public void setfDescrib(String fDescrib) {
        this.fDescrib = fDescrib;
    }

    public List<SysFunction> getSysFunctions() {
        return sysFunctions;
    }

    public void setSysFunctions(List<SysFunction> sysFunctions) {
        this.sysFunctions = sysFunctions;
    }
}
