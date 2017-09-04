package com.xwke.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xwke.api.userauth.UserAuthDTO;
import com.xwke.spider.modle.UserAuthModle;

public class SpringSecurityUserAuth extends UserAuthModle implements UserDetails {
	
	private Collection<? extends GrantedAuthority> authorities;
	private List<String> permissions;
	private List<String> roles;

	private String tenantId;

	public String getTenantId() {
		if (StringUtil.isBlank(tenantId)) {
			tenantId = String.valueOf(id);
		}

		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

		

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	// ~ ==================================================
	public void setPermissions(List<String> permissions) {

		if (authorities != null) {
			return;
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		for (String permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission));
		}

		this.authorities = authorities;
	}

	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof UserAuthDTO) {
			return this.getUsername().equals(((UserAuthDTO) rhs).getUsername());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getUsername().hashCode();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
}
