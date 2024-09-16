package spring_security.spring_security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring_security.spring_security.enums.RoleEnum;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USUARIOS")
public class Usuario implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "nome",nullable = false)
    private String nome;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "senha",nullable = false)
    private String senha;
    @Column(name = "role",nullable = false)
    private RoleEnum role;

    public Usuario(String n,String l,String s,RoleEnum r){
        this.nome = n;
        this.login = l;
        this.senha =s;
        this.role = r;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RoleEnum.ADMIN){
            return List.of(
              new SimpleGrantedAuthority("ROLE_ADMIN"),
              new SimpleGrantedAuthority("ROLE_USER")
            );
        }

        return List.of(
            new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
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
