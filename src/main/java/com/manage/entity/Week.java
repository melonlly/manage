package com.manage.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_exam_week")
public class Week {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name; // 学生姓名
	private String week1;
	private String week2;
	private String week3;
	private String week4;
	private String week5;
	private String week6;
	private String week7;
	private String week8;
	private String week9;
	private String week10;
	private String week11;
	private String week12;
	private String week13;
	private String week14;

	public Week() {}

	public Week(String name, String week1, String week2, String week3, String week4, String week5, String week6, String week7, String week8, String week9, String week10, String week11, String week12, String week13, String week14) {
		this.name = name;
		this.week1 = week1;
		this.week2 = week2;
		this.week3 = week3;
		this.week4 = week4;
		this.week5 = week5;
		this.week6 = week6;
		this.week7 = week7;
		this.week8 = week8;
		this.week9 = week9;
		this.week10 = week10;
		this.week11 = week11;
		this.week12 = week12;
		this.week13 = week13;
		this.week14 = week14;
	}

	@Override
	public String toString() {
		return "Week{" +
				"id=" + id +
				", name='" + name + '\'' +
				", week1='" + week1 + '\'' +
				", week2='" + week2 + '\'' +
				", week3='" + week3 + '\'' +
				", week4='" + week4 + '\'' +
				", week5='" + week5 + '\'' +
				", week6='" + week6 + '\'' +
				", week7='" + week7 + '\'' +
				", week8='" + week8 + '\'' +
				", week9='" + week9 + '\'' +
				", week10='" + week10 + '\'' +
				", week11='" + week11 + '\'' +
				", week12='" + week12 + '\'' +
				", week13='" + week13 + '\'' +
				", week14='" + week14 + '\'' +
				'}';
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

	public String getWeek1() {
		return week1;
	}

	public void setWeek1(String week1) {
		this.week1 = week1;
	}

	public String getWeek2() {
		return week2;
	}

	public void setWeek2(String week2) {
		this.week2 = week2;
	}

	public String getWeek3() {
		return week3;
	}

	public void setWeek3(String week3) {
		this.week3 = week3;
	}

	public String getWeek4() {
		return week4;
	}

	public void setWeek4(String week4) {
		this.week4 = week4;
	}

	public String getWeek5() {
		return week5;
	}

	public void setWeek5(String week5) {
		this.week5 = week5;
	}

	public String getWeek6() {
		return week6;
	}

	public void setWeek6(String week6) {
		this.week6 = week6;
	}

	public String getWeek7() {
		return week7;
	}

	public void setWeek7(String week7) {
		this.week7 = week7;
	}

	public String getWeek8() {
		return week8;
	}

	public void setWeek8(String week8) {
		this.week8 = week8;
	}

	public String getWeek9() {
		return week9;
	}

	public void setWeek9(String week9) {
		this.week9 = week9;
	}

	public String getWeek10() {
		return week10;
	}

	public void setWeek10(String week10) {
		this.week10 = week10;
	}

	public String getWeek11() {
		return week11;
	}

	public void setWeek11(String week11) {
		this.week11 = week11;
	}

	public String getWeek12() {
		return week12;
	}

	public void setWeek12(String week12) {
		this.week12 = week12;
	}

	public String getWeek13() {
		return week13;
	}

	public void setWeek13(String week13) {
		this.week13 = week13;
	}

	public String getWeek14() {
		return week14;
	}

	public void setWeek14(String week14) {
		this.week14 = week14;
	}
}
