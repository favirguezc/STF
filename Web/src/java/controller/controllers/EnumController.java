/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import javax.faces.bean.ManagedBean;
import model.administration.BloodGroupEnum;
import model.administration.PageEnum;
import model.administration.RHEnum;
import model.administration.RoleEnum;
import model.administration.SexEnum;
import model.applications.AplicationMethodEnum;
import model.applications.ApplicationMeasurementUnitEnum;
import model.applications.ApplicationTypeEnum;
import model.crop.ClassificationTypeEnum;
import model.finances.cost.CostItemEnum;
import model.finances.cost.CostTypeEnum;
import model.monitoring.RiskEnum;
import model.monitoring.ValuationTypeEnum;
import model.util.Action;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "enumController")
public class EnumController {

    public ValuationTypeEnum[] getValuationTypeValues() {
        return ValuationTypeEnum.values();
    }

    public SexEnum[] getSexValues() {
        return SexEnum.values();
    }

    public RoleEnum[] getRoleValues() {
        return RoleEnum.values();
    }

    public RiskEnum[] getRiskValues() {
        return RiskEnum.values();
    }

    public PageEnum[] getPageValues() {
        return PageEnum.values();
    }

    public ApplicationMeasurementUnitEnum[] getApplicationMeasurementUnitValues() {
        return ApplicationMeasurementUnitEnum.values();
    }

    public AplicationMethodEnum[] getAplicationMethodValues() {
        return AplicationMethodEnum.values();
    }

    public RHEnum[] getRhFactorValues() {
        return RHEnum.values();
    }

    public BloodGroupEnum[] getBloodGroupValues() {
        return BloodGroupEnum.values();
    }

    public ApplicationTypeEnum[] getApplicationTypeValues() {
        return ApplicationTypeEnum.values();
    }

    public Action[] getActionValues() {
        return Action.values();
    }
    
    public ClassificationTypeEnum[] getClassificationTypeValues(){
        return ClassificationTypeEnum.values();
    }
    
    public CostItemEnum[] getCostItemValues(){
        return CostItemEnum.values();
    }
    
    public CostTypeEnum[] getCostTypeValues(){
        return CostTypeEnum.values();
    }
}
