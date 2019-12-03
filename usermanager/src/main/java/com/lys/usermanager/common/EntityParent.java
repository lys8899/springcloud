package com.lys.usermanager.common;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @description: 实体类必备属性
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 12:21
 **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityParent implements Serializable {

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String fId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fGmtCreate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fGmtModified;

    @Version
    private Long version;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public Date getfGmtCreate() {
        return fGmtCreate;
    }

    public void setfGmtCreate(Date fGmtCreate) {
        this.fGmtCreate = fGmtCreate;
    }

    public Date getfGmtModified() {
        return fGmtModified;
    }

    public void setfGmtModified(Date fGmtModified) {
        this.fGmtModified = fGmtModified;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
