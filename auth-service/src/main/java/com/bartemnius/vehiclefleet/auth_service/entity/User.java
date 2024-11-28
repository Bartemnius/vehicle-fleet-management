package com.bartemnius.vehiclefleet.auth_service.entity;

import com.bartemnius.vehiclefleet.auth_service.utils.Role;
import com.bartemnius.vehiclefleet.auth_service.utils.TwoFactorMethod;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@Entity
@Table(name = "app_user") // user is a key word in postgres
public class User implements UserDetails, CredentialsContainer {
  public User(
      String username,
      String password,
      String email,
      String phoneNumber,
      Role role,
      boolean isTwoFactorEnabled,
      TwoFactorMethod twoFactorMethod) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.isTwoFactorEnabled = isTwoFactorEnabled;
    this.twoFactorMethod = twoFactorMethod;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String email;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  private boolean isTwoFactorEnabled = false;

  @Enumerated(EnumType.STRING)
  private TwoFactorMethod twoFactorMethod;

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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
  }

  @Override
  public void eraseCredentials() {
    this.password = null;
  }
}
