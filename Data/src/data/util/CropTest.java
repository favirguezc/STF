/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import data.administration.ContractDAO;
import data.administration.CultivationDAO;
import data.administration.FarmDAO;
import data.crop.CropDAO;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import model.administration.Cultivation;
import model.administration.Farm;
import model.administration.Person;
import model.administration.RoleEnum;
import model.crop.Crop;
import model.util.DateTools;

/**
 *
 * @author fredy
 */
public class CropTest {

    public static void main(String[] args) {
        try {
            CropDAO controller = new CropDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
            Farm farm = new FarmDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findFarmEntities().get(0);
            List<Person> workers = new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonEntities(RoleEnum.WORKER, farm);
            List<Cultivation> cultivations = new CultivationDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findCultivationEntities();
            Calendar c = GregorianCalendar.getInstance();
            for (int y = 2014; y < 2015; y++) {
                System.out.println("año " + y);
                c.setTime(DateTools.getDate(y, 0, 1));
                for (int i = 0; i < 365; i++) {
                    System.out.print((i + 1) + " ");
                    for (int r = 0; r < 15; r++) {
                        Crop newCrop = new Crop();
                        newCrop.setCollector(workers.get((int) ((Math.random() * workers.size() + 1) - 1)));
                        newCrop.setCultivation(cultivations.get((int) ((Math.random() * cultivations.size() + 1) - 1)));
                        newCrop.setHarvestDate(c.getTime());
                        newCrop.setWeight((float) Math.random() * 10000);
                        controller.create(newCrop);
                    }
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
