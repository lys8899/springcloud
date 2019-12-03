package com.lys.usermanager.entity;

import com.lys.usermanager.common.EntityParent;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_function")
public class SysRowFunction extends EntityParent {
    private String fFunctionId;
    private String fRoleId;

    public String getfFunctionId() {
        return fFunctionId;
    }

    public void setfFunctionId(String fFunctionId) {
        this.fFunctionId = fFunctionId;
    }

    public String getfRoleId() {
        return fRoleId;
    }

    public void setfRoleId(String fRoleId) {
        this.fRoleId = fRoleId;
    }
}
