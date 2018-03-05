package com.manage.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_catalog")
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer parentid; // 父级栏目id
	private String name; // 栏目名
	private String url; // 栏目对应模块名

	public Catalog() {}

	public Catalog(Integer parentid, String name, String url) {
		this.parentid = parentid;
		this.name = name;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
