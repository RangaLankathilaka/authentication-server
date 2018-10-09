package com.osanda.oauth2.authenticationserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@Audited
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAuditEntity {

    @NotNull
    @Pattern(regexp = "^[a-z0-9]*$")
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private Boolean active;

    @NotNull
    @Size(min = 5, max = 60)
    @Column(name = "password", length = 60)
    private String password;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetDate;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }// End set Password
}
