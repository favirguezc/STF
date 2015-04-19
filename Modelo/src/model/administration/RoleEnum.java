package model.administration;

/**
 *
 * @author fredy
 */
public enum RoleEnum {

    MANAGER,
    /**
     *
     */
    PARTNER,
    /**
     *
     */
    FIELD_BOSS,
    /**
     *
     */
    ADMINISTRATIVE_ASSISTANT,
    /**
     *
     */
    ACCOUNTANT,
    /**
     *
     */
    SPECIALIST,
    /**
     *
     */
    WORKER,
    /**
     *
     */
    CLIENT;

    @Override
    public String toString() {
        switch (this) {
            case ADMINISTRATIVE_ASSISTANT:
                return "Asistente Administrativo";
            case CLIENT:
                return "Cliente";
            case ACCOUNTANT:
                return "Contador";
            case SPECIALIST:
                return "Especialista";
            case MANAGER:
                return "Gerente";
            case FIELD_BOSS:
                return "Jefe de Campo";
            case PARTNER:
                return "Socio";
            case WORKER:
                return "Trabajador";
            default:
                return "";
        }
    }

}
