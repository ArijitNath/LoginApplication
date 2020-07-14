package org.midas.analytics.loginapplication.model;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "loginDetails")
public class LoginDetails {
	
	@PrimaryKey
	private String loginId;
	private String loginUserName;
	private String loginEmailID;
	private String loginPassword;
	private Date lastLoginTime;
	
	public String getLoginId() { return loginId; }
	
	public void setLoginId(String loginId) { this.loginId = loginId; }
	
	public String getLoginUserName() { return loginUserName; }
	
	public void setLoginUserName(String loginUserName) { this.loginUserName = loginUserName; }
	
	public String getLoginEmailID() { return loginEmailID; }
	
	public void setLoginEmailID(String loginEmailID) { this.loginEmailID = loginEmailID; }
	
	public String getLoginPassword() { return loginPassword; }
	
	public void setLoginPassword(String loginPassword) { this.loginPassword = loginPassword; }
	
	public Date getLastLoginTime() { return lastLoginTime; }
	
	public void setLastLoginTime(Date lastLoginTime) { this.lastLoginTime = lastLoginTime; }	
}
