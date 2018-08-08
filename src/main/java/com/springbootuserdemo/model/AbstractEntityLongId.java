package com.springbootuserdemo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import javax.persistence.GenerationType;

@MappedSuperclass
public abstract class AbstractEntityLongId {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	protected Long id;

	@Column(name = "INS_DATE")
	protected Date insDate;

	@Column(name = "INS_USER")
	protected String insUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInsDate() {
		return insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	public String getInsUser() {
		return insUser;
	}

	public void setInsUser(String insUser) {
		this.insUser = insUser;
	}
}
