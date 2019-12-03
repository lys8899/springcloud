package com.lys.usermanager.entity;

import com.lys.usermanager.common.EntityParent;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_row")
public class SysUserRow extends EntityParent {
    private String fUserId;
    private String fRoleId;

    public String getfUserId() {
        return fUserId;
    }

    public void setfUserId(String fUserId) {
        this.fUserId = fUserId;
    }

    public String getfRoleId() {
        return fRoleId;
    }

    public void setfRoleId(String fRoleId) {
        this.fRoleId = fRoleId;
    }
}
