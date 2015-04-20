package model.administration;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fredy
 */
@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(nullable = false)
    private String name;
    private String name2;
    @Column(nullable = false)
    private String lastName;
    private String lastName2;
    @Column(unique = true, nullable = false)
    private long identityNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    @Column(nullable = false)
    private long mobilePhone;
    @Enumerated(EnumType.STRING)
    private BloodGroupEnum boodGroup;
    @Enumerated(EnumType.STRING)
    private RHEnum rhFactor;
    private boolean systemAdmin;

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getName2() {
        return name2;
    }

    /**
     *
     * @param name2
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getLastName2() {
        return lastName2;
    }

    /**
     *
     * @param lastName2
     */
    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    /**
     *
     * @return
     */
    public long getIdentityNumber() {
        return identityNumber;
    }

    /**
     *
     * @param identityNumber
     */
    public void setIdentityNumber(long identityNumber) {
        this.identityNumber = identityNumber;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public SexEnum getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     */
    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    /**
     *
     * @return
     */
    public long getMobilePhone() {
        return mobilePhone;
    }

    /**
     *
     * @param mobilePhone
     */
    public void setMobilePhone(long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     *
     * @return
     */
    public BloodGroupEnum getBloodGroup() {
        return boodGroup;
    }

    /**
     *
     * @param boodGroup
     */
    public void setBloodGroup(BloodGroupEnum boodGroup) {
        this.boodGroup = boodGroup;
    }

    /**
     *
     * @return
     */
    public RHEnum getRhFactor() {
        return rhFactor;
    }

    /**
     *
     * @param rhFactor
     */
    public void setRhFactor(RHEnum rhFactor) {
        this.rhFactor = rhFactor;
    }

    public boolean isSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(boolean systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    @Override
    public String toString() {
        String cadena = name + " ";
        if (name2 != null) {
            cadena += name2 + " ";
        }
        cadena += lastName + " ";
        return cadena;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.identityNumber ^ (this.identityNumber >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.name2, other.name2)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.lastName2, other.lastName2)) {
            return false;
        }
        if (this.identityNumber != other.identityNumber) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.sex != other.sex) {
            return false;
        }
        if (this.mobilePhone != other.mobilePhone) {
            return false;
        }
        if (this.boodGroup != other.boodGroup) {
            return false;
        }
        if (this.rhFactor != other.rhFactor) {
            return false;
        }
        return true;
    }

}
