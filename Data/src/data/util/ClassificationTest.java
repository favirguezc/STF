/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import data.administration.ContractDAO;
import data.administration.CultivationDAO;
import data.administration.FarmDAO;
import data.crop.ClassificationDAO;
import data.crop.CropDAO;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import model.administration.Cultivation;
import model.administration.Farm;
import model.administration.Person;
import model.administration.RoleEnum;
import model.crop.Classification;
import model.crop.ClassificationTypeEnum;
import model.crop.Crop;
import model.util.DateTools;

/**
 *
 * @author fredy
 */
public class ClassificationTest {

    public static void main(String[] args) {
        try {
            ClassificationDAO controller = new ClassificationDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
            Farm farm = new FarmDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findFarmEntities().get(1);
            System.out.println(farm.getName());
            List<Cultivation> cultivations = new CultivationDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findCultivationEntities();
            Calendar c = GregorianCalendar.getInstance();
            for (int y = 2015; y <= 2015; y++) {
                System.out.println("año " + y);
                c.setTime(DateTools.getDate(y, 0, 1));
                for (int i = 0; i < 365; i++) {
                    System.out.print((i + 1) + " ");
                    for (int r = 0; r < 10; r++) {
                        Classification newClassification = new Classification();
                        newClassification.setType(ClassificationTypeEnum.values()[(int) (Math.random() * ClassificationTypeEnum.values().length + 1) - 1]);
                        newClassification.setCultivation(cultivations.get((int) ((Math.random() * cultivations.size() + 1) - 1)));
                        newClassification.setClassificationDate(c.getTime());
                        newClassification.setWeight((float) Math.random() * 10000);
                        controller.create(newClassification);
                    }
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
