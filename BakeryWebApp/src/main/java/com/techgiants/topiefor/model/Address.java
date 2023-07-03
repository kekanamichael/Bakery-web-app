package com.techgiants.topiefor.model;



import com.techgiants.topiefor.exception.ArgumentException;
import java.util.Objects;

public class Address {
    private String streetName, city, province, country,suburb,postalCode;
    private int addressId;

    public Address() {
    }

    public Address(String streetName, String city, String province, String country, String suburb, String postalCode, int addressId) {
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.country = country;
        this.suburb = suburb;
        this.postalCode = postalCode;
        this.addressId = addressId;
    }
    
    

    public Address(String streetName, String city, String state, String country, String postalCode) {
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Address(String streetName, String city, String province, String country, String suburb, String postalCode) {
        this.streetName = streetName;
        this.city = city;
        this.province = province;
        this.country = country;
        this.suburb = suburb;
        this.postalCode = postalCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) throws ArgumentException {
        if(streetName==null || streetName.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws ArgumentException {
        if(city==null || city.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) throws ArgumentException {
        if(province==null || province.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws ArgumentException {
        if(country==null || country.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws ArgumentException {
        if(postalCode==null || postalCode.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" + "streetName=" + streetName + ", city=" + city + ", state=" + province + ", country=" + country + ", postalCode=" + postalCode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.streetName);
        hash = 79 * hash + Objects.hashCode(this.city);
        hash = 79 * hash + Objects.hashCode(this.province);
        hash = 79 * hash + Objects.hashCode(this.country);
        hash = 79 * hash + Objects.hashCode(this.postalCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        return true;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    
}
