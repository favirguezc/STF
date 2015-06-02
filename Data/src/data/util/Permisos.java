/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import data.administration.PermissionDAO;
import model.administration.PageEnum;
import model.administration.Permission;
import model.administration.RoleEnum;
import model.util.Action;

/**
 *
 * @author fredy
 */
public class Permisos {
    public static void main(String[] args) {
        for(PageEnum p:PageEnum.values()){
            for(Action a:Action.values()){
                Permission per = new Permission();
                per.setActionEnum(a);
                per.setPageEnum(p);
                per.setRoleEnum(RoleEnum.ADMINISTRATIVE_ASSISTANT);
                new PermissionDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).create(per);
            }
        }
    }
}
