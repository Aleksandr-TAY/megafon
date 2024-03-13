package com.hotric.megafon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    
    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String getAuthority() {
        return getName();
    }
}
