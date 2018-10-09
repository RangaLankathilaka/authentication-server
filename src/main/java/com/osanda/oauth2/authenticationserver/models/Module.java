package com.osanda.oauth2.authenticationserver.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 0, max = 50)
    @Column(length = 50, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "module", cascade = CascadeType.DETACH)
    private List<Submodule> submodule;
}
