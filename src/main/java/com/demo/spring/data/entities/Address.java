package com.demo.spring.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "address")
public class Address {

	@Id
	private int addressId;
	private String addressCountry;
	private String addressCity;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressCity == null) ? 0 : addressCity.hashCode());
		result = prime * result + ((addressCountry == null) ? 0 : addressCountry.hashCode());
		result = prime * result + addressId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressCity == null) {
			if (other.addressCity != null)
				return false;
		} else if (!addressCity.equals(other.addressCity))
			return false;
		if (addressCountry == null) {
			if (other.addressCountry != null)
				return false;
		} else if (!addressCountry.equals(other.addressCountry))
			return false;
		if (addressId != other.addressId)
			return false;
		return true;
	}
}