
package com.techgiants.topiefor.model;
import com.techgiants.topiefor.exception.ArgumentException;
import java.util.Objects;

public class User {
    private String name, surname, mobileNum, email, password;
    private int userId;
    private Address address;
    private Role role;

    public User() {
    }
    
    


    public User(String name, String surname, String mobileNum, String email, int userId) {
        this.name = name;
        this.surname = surname;
        this.mobileNum = mobileNum;
        this.email = email;
        this.userId = userId;
    }

    public User(String name, String surname, String mobileNum, String email, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.mobileNum = mobileNum;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.address = address;
        this.role = role;
    }

    
    
    

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws ArgumentException {
        if(address==null){
             throw new ArgumentException("Invalid Name.");
        }
        
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) throws ArgumentException {
        if(role==null){
             throw new ArgumentException("Invalid Name.");
        }
        this.role = role;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) throws ArgumentException{
        if(name==null || name.isEmpty()){
             throw new ArgumentException("Invalid Name.");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ArgumentException {
        if(surname==null || surname.isEmpty()){
             throw new ArgumentException("Invalid Surname.");
        }
        this.surname = surname;
    }
    

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) throws ArgumentException {
        if(mobileNum==null || mobileNum.isEmpty()){
             throw new ArgumentException("Invalid Mobile number.");
        }
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ArgumentException {
        if(email==null || email.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) throws ArgumentException {
        if(userId<0){
             throw new ArgumentException("Invalid User email.");
        }
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", surname=" + surname + ", mobileNum=" + mobileNum + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.surname);
        hash = 53 * hash + Objects.hashCode(this.mobileNum);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + this.userId;
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
        final User other = (User) obj;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ArgumentException {
        if(email==null || email.isEmpty()){
             throw new ArgumentException("Invalid User email.");
        }
        
        this.password = password;
    }
    
    
}
