package com.stargate.demo.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "MODULE_TBL")
public class ModuleTbl {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	@Column
	private String module;
	@Column
	private String majorType;
	@Column
	private String mainType;
	@Column
	private String formOutput;
	@Column
	private Date createdDate;

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMajorType() {
		return majorType;
	}

	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getFormOutput() {
		return formOutput;
	}

	public void setFormOutput(String formOutput) {
		this.formOutput = formOutput;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
