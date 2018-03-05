package com.manage.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //id主键
	private String name; //用户名
	private String password; //用户密码
	private String create_time; // 创建时间（yyyy-MM-dd hh:mm:ss）
	private Integer is_admin; // 是否管理员（0：不是,1：是）
	private Integer status; // 状态（0：无效,1：有效）
	private Integer role; // 角色（1：老师,2：家长）
	private String relation; // 关系人姓名
	private String phone; // 手机

	public User() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(Integer is_admin) {
		this.is_admin = is_admin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", create_time='" + create_time + '\'' +
				", is_admin=" + is_admin +
				", status=" + status +
				", role=" + role +
				", relation='" + relation + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
