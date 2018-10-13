package com.osanda.oauth2.authenticationserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Audited
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date resetDate;

    @NotAudited
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = true)})
    private Set<Module> modules = new HashSet<>();

    @NotAudited
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "sub_module_id", referencedColumnName = "id", nullable = true)})
    private Set<Submodule> subModules = new HashSet<>();

    @NotAudited
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = true)})
    private Set<Role> roles = new HashSet<>();

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }// End set Password
}
