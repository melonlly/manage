package com.manage.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_lesson")
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //id主键
	private String semester; // 学期
	private String period;
	private String time;
	private String day1;
	private String day2;
	private String day3;
	private String day4;
	private String day5;

	public Lesson() {}

	public Lesson(String semester, String period, String time, String day1, String day2, String day3, String day4, String day5) {
		this.semester = semester;
		this.period = period;
		this.time = time;
		this.day1 = day1;
		this.day2 = day2;
		this.day3 = day3;
		this.day4 = day4;
		this.day5 = day5;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay1() {
		return day1;
	}

	public void setDay1(String day1) {
		this.day1 = day1;
	}

	public String getDay2() {
		return day2;
	}

	public void setDay2(String day2) {
		this.day2 = day2;
	}

	public String getDay3() {
		return day3;
	}

	public void setDay3(String day3) {
		this.day3 = day3;
	}

	public String getDay4() {
		return day4;
	}

	public void setDay4(String day4) {
		this.day4 = day4;
	}

	public String getDay5() {
		return day5;
	}

	public void setDay5(String day5) {
		this.day5 = day5;
	}

	@Override
	public String toString() {
		return "Lesson{" +
				"id=" + id +
				", semester='" + semester + '\'' +
				", period='" + period + '\'' +
				", time='" + time + '\'' +
				", day1='" + day1 + '\'' +
				", day2='" + day2 + '\'' +
				", day3='" + day3 + '\'' +
				", day4='" + day4 + '\'' +
				", day5='" + day5 + '\'' +
				'}';
	}
}
