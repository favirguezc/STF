/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import data.work.JobDAO;
import model.work.Job;

/**
 *
 * @author fredy
 */
public class Jobs {

    public static void main(String[] args) {
        String[] jobs = {
            "Aplicación Agroquímicos",
            "Aplicación de herbicidas pre-siembra",
            "Arrancada de plantas",
            "Cargue y descargue de plantas",
            "Clavado de patas",
            "Control de malezas en camas y calles",
            "Desclavado de patas",
            "Fertilización",
            "Fertilización de presiembra",
            "Implementación del sistema de riego",
            "Instalación de arcos",
            "Instalación de mulch (plástico)",
            "Levantada de camas",
            "Mantenimiento de macrotúneles",
            "Marcado y perforación del plástico",
            "Poda Fitosanitaria",
            "Postura de ganchos",
            "Postura plásticos macros",
            "Preparación de camas",
            "Recolección y clasificación de fruta",
            "Reproceso de fruta",
            "Resiembra de plantas",
            "Retirada de plástico macrotúnel",
            "Retiro de arcos",
            "Retiro de estolones",
            "Siembra",
            "Subida plástico",
            "Sunchar"
        };
        JobDAO jobDAO = new JobDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        for (String job : jobs) {
            try {
                if (jobDAO.findJob(job) == null) {
                    jobDAO.create(new Job(job, null, 3100)); //Provisional
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                jobDAO.create(new Job(job, null, 3100)); //Provisional
            }
        }
    }
}
