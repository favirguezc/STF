package modelo.produccion;

import modelo.produccion.ProductoFitosanitario;
import modelo.administracion.Persona;
import modelo.administracion.Lote;

/***********************************************************************
 * Module:  AplicacionFitosanitaria.java
 * Author:  fredy
 * Purpose: Defines the Class AplicacionFitosanitaria
 ***********************************************************************/

/** @pdOid 3884a8c6-bb77-4178-93cb-a5aecdf9dd01 */
public class AplicacionFitosanitaria {
   /** @pdOid 319323f9-f632-40c8-a281-3fe30dc3a895 */
   private long id;
   /** @pdOid c531a5bd-5288-49fa-98df-26cc6d753faa */
   private java.util.Calendar fecha;
   /** @pdOid fc572826-453e-4393-8bd9-3b83216d9db3 */
   private ProductoFitosanitario producto;
   /** @pdOid 24c1c431-9f7a-450b-8265-943b12fb1563 */
   private java.lang.String motivo;
   /** @pdOid b73f0b8a-d03f-439d-9305-212d51ed006b */
   private boolean pc;
   /** @pdOid 0632ca2a-592a-47ce-b942-27eaa9a1e4a4 */
   private boolean tr;
   /** @pdOid b9a50eb4-518c-45e1-86cb-c325175c5303 */
   private float cantidad;
   /** @pdOid d0d4c7b1-e762-4fab-a493-e0641ae7f382 */
   private float agua;
   /** @pdOid 3b1dc481-9409-4853-b015-bec1f8a09982 */
   private java.lang.String equipo;
   /** @pdOid f80834d6-a25f-41e7-b6d6-453b103a4363 */
   private Persona responsable;
   /** @pdOid 38a57a19-2dbd-406d-87ea-2178a55b8f8e */
   private Persona aprobante;
   /** @pdOid 801a55bc-df94-40f8-b3fc-64ad1172206b */
   private float jornales;
   /** @pdOid 65b114f7-d8c7-4167-8b71-4e7c7b8b74e1 */
   private java.lang.String observaciones;
   /** @pdOid 11e038a2-f17c-43b0-ad94-0db75349533d */
   private Lote lote;
   /** @pdOid eb43bb5b-547a-4c5d-ab2e-07d5e1b1d267 */
   private Persona asistente;
   /** @pdOid 5b5a4a5d-c186-4b86-8a3b-c32cbcbcd75b */
   private Persona productor;

}