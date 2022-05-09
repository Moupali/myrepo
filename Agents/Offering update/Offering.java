package com.example.model;

public class Offering {

	
	private String _id;
	private String _rev;
	private String flow;
	private String brand;
	private String cftype;
	private String maintenance;
	private String level30code;
	private String offeringname;
	private String boname;
	private String imt;
	private String level20code;
	private Number created;
	private String creator;
	private String utlevel30des;
	private String skipLeadFromMassload;
	private String offeringfocalpoint;
	private String offeringopportunityowner;
	private String status;
	private String maximumPayout;
	private String defaultBonusPercentage;

	
	public String getMaximumPayout() {
		return maximumPayout;
	}

	public void setMaximumPayout(String maximumPayout) {
		this.maximumPayout = maximumPayout;
	}

	public String getDefaultBonusPercentage() {
		return defaultBonusPercentage;
	}

	public void setDefaultBonusPercentage(String defaultBonusPercentage) {
		this.defaultBonusPercentage = defaultBonusPercentage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfferingfocalpoint() {
		return offeringfocalpoint;
	}

	public void setOfferingfocalpoint(String offeringfocalpoint) {
		this.offeringfocalpoint = offeringfocalpoint;
	}

	public String getOfferingopportunityowner() {
		return offeringopportunityowner;
	}

	public void setOfferingopportunityowner(String offeringopportunityowner) {
		this.offeringopportunityowner = offeringopportunityowner;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCftype() {
		return cftype;
	}

	public void setCftype(String cftype) {
		this.cftype = cftype;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	public String getLevel30code() {
		return level30code;
	}

	public void setLevel30code(String level30code) {
		this.level30code = level30code;
	}

	public String getOfferingname() {
		return offeringname;
	}

	public void setOfferingname(String offeringname) {
		this.offeringname = offeringname;
	}

	public String getBoname() {
		return boname;
	}

	public void setBoname(String boname) {
		this.boname = boname;
	}

	public String getImt() {
		return imt;
	}

	public void setImt(String imt) {
		this.imt = imt;
	}

	public String getLevel20code() {
		return level20code;
	}

	public void setLevel20code(String level20code) {
		this.level20code = level20code;
	}

	public Number getCreated() {
		return created;
	}

	public void setCreated(Number created) {
		this.created = created;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUtlevel30des() {
		return utlevel30des;
	}

	public void setUtlevel30des(String utlevel30des) {
		this.utlevel30des = utlevel30des;
	}

	public String getSkipLeadFromMassload() {
		return skipLeadFromMassload;
	}

	public void setSkipLeadFromMassload(String skipLeadFromMassload) {
		this.skipLeadFromMassload = skipLeadFromMassload;
	}

	@Override
	public String toString() {
		return "Offering [_id=" + _id + ", _rev=" + _rev + ", flow=" + flow + ", brand=" + brand + ", cftype=" + cftype
				+ ", maintenance=" + maintenance + ", level30code=" + level30code + ", offeringname=" + offeringname
				+ ", boname=" + boname + ", imt=" + imt + ", level20code=" + level20code + ", created=" + created
				+ ", creator=" + creator + ", utlevel30des=" + utlevel30des + ", skipLeadFromMassload="
				+ skipLeadFromMassload + ", offeringfocalpoint=" + offeringfocalpoint + ", offeringopportunityowner="
				+ offeringopportunityowner + ", status=" + status + ", maximumPayout=" + maximumPayout
				+ ", defaultBonusPercentage=" + defaultBonusPercentage + "]";
	}

	
}
