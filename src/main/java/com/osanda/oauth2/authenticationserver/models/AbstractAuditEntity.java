package com.osanda.oauth2.authenticationserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by", nullable = true, length = 50, updatable = false)
    private String createdBy;

    @CreatedDate
    @JsonIgnore
    @Column(name = "created_date", nullable = true)
    private Date createdDate = new Date();

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = true, length = 50)
    private String lastModifiedBy;

    @JsonIgnore
    @LastModifiedDate
    private Date lastModifiedDate = new Date();

    @Version
    @JsonIgnore
    private Long version;
}
