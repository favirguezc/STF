package modelo.produccion.administracion;

public enum Rol {
    RECOLECTOR,
    TRABAJADOR,
    ADMINISTRADOR,
    GERENTE,
    ASISTENTE_TECNICO,
    SECRETARIO,
    CLIENTE;

    @Override
    public String toString() {
        switch (this) {
            case ADMINISTRADOR:
                return "Administrador";
            case ASISTENTE_TECNICO:
                return "Asistente Técnico";
            case RECOLECTOR:
                return "Recolector";
            case SECRETARIO:
                return "Secretario";
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
