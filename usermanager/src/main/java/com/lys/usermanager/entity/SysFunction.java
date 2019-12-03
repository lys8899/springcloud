package com.lys.usermanager.entity;

import com.lys.usermanager.common.EntityParent;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @description: 权限
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 12:44
 **/
@Entity
@Table(name = "sys_function")
public class SysFunction extends EntityParent implements GrantedAuthority {

    private String fName;

    private String fMark;

    private Integer fSystemId;

    private Boolean fClose;

    private Integer fParenId;

    @Override
    public String getAuthority() {
        return fMark;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfMark() {
        return fMark;
    }

    public void setfMark(String fMark) {
        this.fMark = fMark;
    }

    public Integer getfSystemId() {
        return fSystemId;
    }

    public void setfSystemId(Integer fSystemId) {
        this.fSystemId = fSystemId;
    }

    public Boolean getfClose() {
        return fClose;
    }

    public void setfClose(Boolean fClose) {
        this.fClose = fClose;
    }

    public Integer getfParenId() {
        return fParenId;
    }

    public void setfParenId(Integer fParenId) {
        this.fParenId = fParenId;
    }
}
