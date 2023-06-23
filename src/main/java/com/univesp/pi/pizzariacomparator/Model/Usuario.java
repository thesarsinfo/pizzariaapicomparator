package com.univesp.pi.pizzariacomparator.Model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;

    @Size(max = 25)
	@Column(nullable = false)
	@NotBlank
    private String nome;
    
	@Column(nullable = false, unique = true)
	@NotBlank
    private String email;

    @Column(nullable = false)
	@NotBlank
    private String senha;

    @ManyToMany
    @JoinTable(name = "tb_usuario_roles",
        joinColumns =  @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
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
}
