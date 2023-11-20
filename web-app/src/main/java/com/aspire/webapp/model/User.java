package com.aspire.webapp.model;

public class User {
		private Long id;
		private String username;
		private String password;
		private String repassword;
		private String role;

		public User() {
			super();
		}
		
		public User(String username, String password ) {
			super();
			this.username = username;
			this.password = password;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
		public String getRepassword() {
			return repassword;
		}
		public void setRepassword(String repassword) {
			this.repassword = repassword;
		}
}
