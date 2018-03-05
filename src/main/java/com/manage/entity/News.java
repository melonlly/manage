package com.manage.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name; // 消息名称
	private String create_time; // 创建时间（yyyy-MM-dd hh:mm:ss）
	private String update_time; // 更新时间（yyyy-MM-dd hh:mm:ss）
	private String publisher; // 发布者
	private String updater; // 更新者
	private String content; // 内容

	public News() {
	}

	public News(String name, String create_time, String update_time, String publisher, String updater, String content) {
		this.name = name;
		this.create_time = create_time;
		this.update_time = update_time;
		this.publisher = publisher;
		this.updater = updater;
		this.content = content;
	}

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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "News{" +
				"id=" + id +
				", name='" + name + '\'' +
				", create_time='" + create_time + '\'' +
				", update_time='" + update_time + '\'' +
				", publisher='" + publisher + '\'' +
				", updater='" + updater + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
