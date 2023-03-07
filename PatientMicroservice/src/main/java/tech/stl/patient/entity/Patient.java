package tech.stl.patient.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "patient")
@SQLDelete(sql = "UPDATE patient SET deleted = true WHERE patientId=?")
@Where(clause = "deleted = false")
public class Patient implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int patientId;
    @NotEmpty(message = "Patient name must not be Empty")
    private String patientName;
   
    @NotEmpty
    @Email(message = "Provide valid email")
    private String emailId;
    @NotEmpty
    @Size(min = 10, message = "contact should have at least 10 characters")
    private String mobileNo;
    
    private String password;

    public Patient(String password) {
        super();
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    private boolean deleted = Boolean.FALSE;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Patient(int patientId, @NotEmpty String patientName, @NotEmpty String emailId, @NotEmpty String mobileNo) {
        super();
        this.patientId = patientId;
        this.patientName = patientName;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    public Patient() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", emailId=" + emailId
                + ", mobileNo=" + mobileNo + "]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
