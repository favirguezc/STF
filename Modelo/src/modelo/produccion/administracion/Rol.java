package modelo.produccion.administracion;

/**
 *
 * @author fredy
 */
public enum Rol {

    GERENTE,
    /**
     *
     */
    SOCIO,
    /**
     *
     */
    JEFE_DE_CAMPO,
    /**
     *
     */
    ASISTENTE_ADMINISTRATIVO,
    /**
     *
     */
    CONTADOR,
    /**
     *
     */
    ESPECIALISTA,
    /**
     *
     */
    TRABAJADOR,
    /**
     *
     */
    CLIENTE;

    @Override
    public String toString() {
        switch (this) {
            case ASISTENTE_ADMINISTRATIVO:
                return "Asistente Administrativo";
            case CLIENTE:
                return "Cliente";
            case CONTADOR:
                return "Contador";
            case ESPECIALISTA:
                return "Especialista";
            case GERENTE:
                return "Gerente";
            case JEFE_DE_CAMPO:
                return "Jefe de Campo";
            case SOCIO:
                return "Socio";
            case TRABAJADOR:
                return "Trabajador";
            default:
                return "";
        }
    }

}
