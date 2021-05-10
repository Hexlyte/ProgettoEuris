package org.generation.italy.euris.model;

import org.generation.italy.euris.util.IMappablePro;

public class Article implements IMappablePro {
	
	private int code;
	private String name;
	private String cost;
	
	public Article(int code, String name, String cost) {
		super();
		this.code = code;
		this.name = name;
		this.cost = cost;
	}

	public Article() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "{code:" + code + ", name:" + name + ", cost:" + cost + "}";
	}
	
}
