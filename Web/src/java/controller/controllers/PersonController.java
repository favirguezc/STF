package controller.controllers;

import model.administration.Person;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.administration.ContractDAO;
import data.administration.PersonDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.RoleEnum;

@ManagedBean(name = "personController")
@SessionScoped
public class PersonController implements Serializable {

    private PersonDAO jpaController = null;
    private List<Person> items = null;
    private Person selected;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public Person getSelected() {
        return selected;
    }

    public void setSelected(Person selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    public SignInController getSignInBean() {
        return signInBean;
    }

    public void setSignInBean(SignInController signInBean) {
        this.signInBean = signInBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PersonDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Person prepareCreate() {
        selected = new Person();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePerson").getString("PersonCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePerson").getString("PersonUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePerson").getString("PersonDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Person> getItems() {
        if (items == null || items.isEmpty()) {
            if (signInBean.getUser().isSystemAdmin()) {
                items = getJpaController().findPersonEntities();
            } else {
                items = new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonEntities(signInBean.getFarm());
            }
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!permissionBean.currentUserHasPermission(persistAction, selected.getClass())) {
                return;
            }
            try {
                if (persistAction == PersistAction.UPDATE) {
                    getJpaController().edit(selected);
                } else if (persistAction == PersistAction.CREATE) {
                    getJpaController().create(selected);
                } else {
                    getJpaController().destroy(selected.getId());
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Person> getItemsAvailableSelectMany() {
        return getItems();

    }

    public List<Person> getItemsAvailableSelectOne() {
        return getItems();
    }

    public List<Person> getItemsAvailableWorker() {
        return new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonEntities(RoleEnum.WORKER, signInBean.getFarm());
    }

    public List<Person> getItemsAvailableSpecialistAndFieldBoss() {
        List<Person> findPersonEntities = new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonEntities(RoleEnum.SPECIALIST, signInBean.getFarm());
        for (Person p : new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonEntities(RoleEnum.FIELD_BOSS, signInBean.getFarm())) {
            if (!findPersonEntities.contains(p)) {
                findPersonEntities.add(p);
            }
        }
        return findPersonEntities;
    }

    public List<Person> getItemsAvailableClient() {
        return new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonEntities(RoleEnum.CLIENT, signInBean.getFarm());
    }

    @FacesConverter(forClass = Person.class)
    public static class PersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonController controller = (PersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personController");
            return controller.getJpaController().findPerson(getKey(value));
        }

        long getKey(String value) {
            long key;
            key = Long.parseLong(value);
            return key;
        }

        String getStringKey(long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Person) {
                Person o = (Person) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Person.class.getName()});
                return null;
            }
        }

    }

}
