package modelo.produccion.administracion;

/**
 *
 * @author fredy
 */
public enum Rol {

    /**
     *
     */
    RECOLECTOR,

    /**
     *
     */
    TRABAJADOR,

    /**
     *
     */
    ADMINISTRADOR,

    /**
     *
     */
    GERENTE,

    /**
     *
     */
    SECRETARIO,
    
    /**
     *
     */
    ASISTENTE_TECNICO,

    /**
     *
     */
    CLIENTE;

    @Override
    public String toString() {
        switch (this) {
            case ADMINISTRADOR:
                return "Administrador";
            case RECOLECTOR:
                return "Recolector";
            case SECRETARIO:
                return "Secretario";
            case ASISTENTE_TECNICO:
                return "Asistente Técnico";
            case TRABAJADOR:
                return "Trabajador";
            case GERENTE:
                return "Gerente";
            case CLIENTE:
                return "Cliente";
            default:
                return "";
        }
    }
}
