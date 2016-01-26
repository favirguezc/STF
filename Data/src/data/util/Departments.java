/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.util;

import data.administration.DepartmentDAO;
import model.administration.Department;

/**
 *
 * @author fredy
 */
public class Departments {

    public static void main(String[] args) {
        String lista = "1.	Amazonas	\n"
                + "2.	Antioquia	\n"
                + "3.	Arauca	\n"
                + "4.	Atlántico	\n"
                + "5.	Bolívar	\n"
                + "6.	Boyacá	\n"
                + "7.	Caldas	\n"
                + "8.	Caquetá	\n"
                + "9.	Casanare \n"
                + "10.	Cauca	\n"
                + "11.	Cesar	\n"
                + "12.	Chocó	\n"
                + "13.	Córdoba	\n"
                + "14.	Cundinamarca	\n"
                + "15.	Guainía	\n"
                + "16.	Guaviare	\n"
                + "17.	Huila	\n"
                + "18.	La Guajira	\n"
                + "19.	Magdalena	\n"
                + "20.	Meta	\n"
                + "21.	Nariño	\n"
                + "22.	Norte de Santander	\n"
                + "23.	Putumayo	\n"
                + "24.	Quindío	\n"
                + "25.	Risaralda	\n"
                + "26.	San Andrés y Providencia	\n"
                + "27.	Santander	\n"
                + "28.	Sucre	\n"
                + "29.	Tolima	\n"
                + "30.	Valle del Cauca	\n"
                + "31.	Vaupés	\n"
                + "32.	Vichada";
        for (String s : lista.split("\n")) {
            System.out.println(s.split("\t")[1]);
            new DepartmentDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).create(new Department(s.split("\t")[1]));
        }
    }
}
