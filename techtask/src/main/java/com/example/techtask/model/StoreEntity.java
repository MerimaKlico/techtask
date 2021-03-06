package com.example.techtask.model;
// default package
// Generated Feb 22, 2019 5:51:06 AM by Hibernate Tools 4.3.5.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Store generated by hbm2java
 */
@Entity
@Table(name = "store", catalog = "techtask")
public class StoreEntity implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk", unique = true, nullable = false)
	private Integer pk;
	
	@Column(name = "name", length = 45)
	private String name;
	
	@Column(name = "address", length = 45)
	private String address;
	
	@Column(name = "longitude", precision = 22, scale = 0)
	private Double longitude;
	
	@Column(name = "latitude", precision = 22, scale = 0)
	private Double latitude;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeEntity")
	private List<CalculatedroutesEntity> calculatedroutes = new ArrayList<CalculatedroutesEntity>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storeEntity")
	private List<GoodsEntity> goods = new ArrayList<GoodsEntity>();

	public StoreEntity() {
	}
	
	public StoreEntity(int pk) {
		this.pk = pk;
	}

	public StoreEntity(String name, String address, Double longitude, Double latitude,
			List<CalculatedroutesEntity> calculatedrouteses, List<GoodsEntity> goodses) {
		this.name = name;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.calculatedroutes = calculatedrouteses;
		this.goods = goodses;
	}

	
	public Integer getPk() {
		return this.pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	
	public List<CalculatedroutesEntity> getCalculatedrouteses() {
		return this.calculatedroutes;
	}

	public void setCalculatedrouteses(List<CalculatedroutesEntity> calculatedrouteses) {
		this.calculatedroutes = calculatedrouteses;
	}

	public List<GoodsEntity> getGoodses() {
		return this.goods;
	}

	public void setGoodses(List<GoodsEntity> goodses) {
		this.goods = goodses;
	}

}
