package com.techgiants.topiefor.model;


import com.techgiants.topiefor.exception.ArgumentException;
import java.util.Objects;

public class Role {

    public Role(String role, int roleId) {
        this.role = role;
        this.roleId = roleId;
    }

   
    
    
    private String role;

  
    private int roleId;


    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) throws ArgumentException {
        if(role==null || role.isEmpty()){
             throw new ArgumentException("Invalid User role.");
        }
        this.role = role;
    }
    
      public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) throws ArgumentException {
        
        if(roleId<0){
             throw new ArgumentException("Invalid role Id.");
             
        }
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Role{" + "role=" + role + ", roleId=" + roleId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.role);
        hash = 29 * hash + this.roleId;
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
        final Role other = (Role) obj;
        return true;
    }
}
