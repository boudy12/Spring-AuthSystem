package com.security.AuthSystem.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.AuthSystem.model.User;

public class CustomUserDetials implements UserDetails{

	private User user;
	
	public CustomUserDetials(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> user.getRole()) ;
	}

	public String getfirstName() {
		return user.getFirstName();
	}
	
	public String getLastName() {
		return user.getLastName();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
		}


	
	@Override
	public String getUsername() {
		return user.getEmail();
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
