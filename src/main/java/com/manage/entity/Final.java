package com.manage.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_exam_final")
public class Final {

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
	private String maths1;
	private String physical; // 物理
	private String physical1;
	private String geography;
	private String music;

	public Final() {}

	public Final(String name, String english, String oral, String social, String science, String chinese, String maths, String maths1, String physical, String physical1, String geography, String music) {
		this.name = name;
		this.english = english;
		this.oral = oral;
		this.social = social;
		this.science = science;
		this.chinese = chinese;
		this.maths = maths;
		this.maths1 = maths1;
		this.physical = physical;
		this.physical1 = physical1;
		this.geography = geography;
		this.music = music;
	}

	@Override
	public String toString() {
		return "Final{" +
				"id=" + id +
				", name='" + name + '\'' +
				", english='" + english + '\'' +
				", oral='" + oral + '\'' +
				", social='" + social + '\'' +
				", science='" + science + '\'' +
				", chinese='" + chinese + '\'' +
				", maths='" + maths + '\'' +
				", maths1='" + maths1 + '\'' +
				", physical='" + physical + '\'' +
				", physical1='" + physical1 + '\'' +
				", geography='" + geography + '\'' +
				", music='" + music + '\'' +
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

	public String getMaths1() {
		return maths1;
	}

	public void setMaths1(String maths1) {
		this.maths1 = maths1;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}

	public String getPhysical1() {
		return physical1;
	}

	public void setPhysical1(String physical1) {
		this.physical1 = physical1;
	}

	public String getGeography() {
		return geography;
	}

	public void setGeography(String geography) {
		this.geography = geography;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}
}
