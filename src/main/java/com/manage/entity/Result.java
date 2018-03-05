package com.manage.entity;

import java.util.List;

/**
 * Created by melon on 2018/2/24.
 */
public class Result {

	private Integer code;
	private String error;
	private List data;
	private Integer total;
	private Integer pages;
	private Integer page;
	private Integer size;

	public Result() {}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
