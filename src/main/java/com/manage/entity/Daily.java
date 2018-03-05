package com.manage.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity
@Table(name = "t_daily")
public class Daily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name; // 学生姓名
	private Integer lesson; // 上课（1,2,3）
	private Integer phone; // 手机（1,2,3）
	private Integer homework; // 作业（1,2,3）
	private Integer daily; // 考勤（1,2）
	private Integer clean; // 卫生（1,2）
	private String date; // 日期（yyyy-MM-dd）

	public Daily() {
	}

	public Daily(String name, Integer lesson, Integer phone, Integer homework, Integer daily, Integer clean, String date) {
		this.name = name;
		this.lesson = lesson;
		this.phone = phone;
		this.homework = homework;
		this.daily = daily;
		this.clean = clean;
		this.date = date;
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

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getHomework() {
		return homework;
	}

	public void setHomework(Integer homework) {
		this.homework = homework;
	}

	public Integer getDaily() {
		return daily;
	}

	public void setDaily(Integer daily) {
		this.daily = daily;
	}

	public Integer getClean() {
		return clean;
	}

	public void setClean(Integer clean) {
		this.clean = clean;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Daily{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lesson=" + lesson +
				", phone=" + phone +
				", homework=" + homework +
				", daily=" + daily +
				", clean=" + clean +
				", date='" + date + '\'' +
				'}';
	}
}
