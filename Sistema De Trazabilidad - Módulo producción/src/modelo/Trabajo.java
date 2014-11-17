package modelo;

import modelo.LaborCultural;
import modelo.administracion.Persona;

/***********************************************************************
 * Module:  Trabajo.java
 * Author:  fredy
 * Purpose: Defines the Class Trabajo
 ***********************************************************************/

/** @pdOid 2fe4ff60-bfd7-4247-b47a-4af0ae3bf9b0 */
public class Trabajo {
   /** @pdOid 2f38ffbf-fbb4-4eeb-bf3b-a00517b1a0a9 */
   private long id;
   /** @pdOid 11f85c69-d3b8-46f1-9818-ef03258553e2 */
   private java.util.Calendar fecha;
   /** @pdOid 989bb0ab-3b21-41ea-ac18-1001c8e66d1e */
   private int modulo;
   /** @pdOid 5c296980-8308-4d4f-8d84-5f924eeab47f */
   private LaborCultural labor;
   /** @pdOid 0021f9a6-8845-4747-bfe6-674ca1136223 */
   private Persona operario;
   /** @pdOid 8d18f41d-0bf1-4ffc-b332-0b89bff0546b */
   private float jornales;
   /** @pdOid a07a011e-7aa5-4487-9a82-2402ab6c3f01 */
   private java.lang.String observaciones;
   /** @pdOid 50f61af3-6526-4317-93d5-2edfd7fc8986 */
   private Persona asistente;
   /** @pdOid eced225b-e73b-4f69-8bd7-ad65bb06deb9 */
   private Persona productor;

}