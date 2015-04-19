/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.administration;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import model.util.Action;

/**
 *
 * @author fredy
 */
@Entity
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PageEnum pageEnum;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
    @Enumerated(EnumType.STRING)
    private Action actionEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PageEnum getPageEnum() {
        return pageEnum;
    }

    public void setPageEnum(PageEnum pageEnum) {
        this.pageEnum = pageEnum;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public Action getActionEnum() {
        return actionEnum;
    }

    public void setActionEnum(Action actionEnum) {
        this.actionEnum = actionEnum;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.pageEnum);
        hash = 61 * hash + Objects.hashCode(this.roleEnum);
        hash = 61 * hash + Objects.hashCode(this.actionEnum);
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
        final Permission other = (Permission) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.pageEnum != other.pageEnum) {
            return false;
        }
        if (this.roleEnum != other.roleEnum) {
            return false;
        }
        if (this.actionEnum != other.actionEnum) {
            return false;
        }
        return true;
    }

}
