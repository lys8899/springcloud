package com.lys.usermanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lys.usermanager.common.EntityParent;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @description: 用户
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 12:36
 **/
@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties("password")
@Document(indexName = "sys_user")
public class SysUser extends EntityParent implements UserDetails {
    private String fName;
    private String fAccount;
    private String fPassword;
    private Boolean fLocked;
    private String fImgUrl;
    private String fPhone;
    private String fDepartmentId;
    private String fStationId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fBirthday;

    @Transient
    private List<SysRole> sysRoles = new ArrayList<>();

    @Transient
    private List<SysFunction> sysFunctions = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysFunctions;
    }

    @Override
    public String getPassword() {
        return fPassword;
    }

    @Override
    public String getUsername() {
        return fAccount;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public List<SysFunction> getSysFunctions() {
        return sysFunctions;
    }

    public void setSysFunctions(List<SysFunction> sysFunctions) {
        this.sysFunctions = sysFunctions;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfAccount() {
        return fAccount;
    }

    public void setfAccount(String fAccount) {
        this.fAccount = fAccount;
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    public Boolean getfLocked() {
        return fLocked;
    }

    public void setfLocked(Boolean fLocked) {
        this.fLocked = fLocked;
    }

    public String getfImgUrl() {
        return fImgUrl;
    }

    public void setfImgUrl(String fImgUrl) {
        this.fImgUrl = fImgUrl;
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone;
    }

    public Date getfBirthday() {
        return fBirthday;
    }

    public void setfBirthday(Date fBirthday) {
        this.fBirthday = fBirthday;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public String getfDepartmentId() {
        return fDepartmentId;
    }

    public void setfDepartmentId(String fDepartmentId) {
        this.fDepartmentId = fDepartmentId;
    }

    public String getfStationId() {
        return fStationId;
    }

    public void setfStationId(String fStationId) {
        this.fStationId = fStationId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "fName='" + fName + '\'' +
                ", fAccount='" + fAccount + '\'' +
                ", fPassword='" + fPassword + '\'' +
                ", fLocked=" + fLocked +
                ", fImgUrl='" + fImgUrl + '\'' +
                ", fPhone='" + fPhone + '\'' +
                ", fDepartmentId='" + fDepartmentId + '\'' +
                ", fStationId='" + fStationId + '\'' +
                ", fBirthday=" + fBirthday +
                ", sysRoles=" + sysRoles +
                ", sysFunctions=" + sysFunctions +
                '}';
    }
}

