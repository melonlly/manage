package com.manage.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_exam_middle")
public class Middle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name; // 学生姓名
	private String english; // 英语
	private String oral;
	private String social; // social
	private String science; // science
	private String chinese; // 中文
	private String maths; // 数学
	private String physical; // 物理

	public Middle() {}

	public Middle(String name, String english, String oral, String social, String science, String chinese, String maths, String physical) {
		this.name = name;
		this.english = english;
		this.oral = oral;
		this.social = social;
		this.science = science;
		this.chinese = chinese;
		this.maths = maths;
		this.physical = physical;
	}

	@Override
	public String toString() {
		return "Middle{" +
				"id=" + id +
				", name='" + name + '\'' +
				", english='" + english + '\'' +
				", oral='" + oral + '\'' +
				", social='" + social + '\'' +
				", science='" + science + '\'' +
				", chinese='" + chinese + '\'' +
				", maths='" + maths + '\'' +
				", physical='" + physical + '\'' +
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

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getOral() {
		return oral;
	}

	public void setOral(String oral) {
		this.oral = oral;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public String getScience() {
		return science;
	}

	public void setScience(String science) {
		this.science = science;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getMaths() {
		return maths;
	}

	public void setMaths(String maths) {
		this.maths = maths;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}
}
