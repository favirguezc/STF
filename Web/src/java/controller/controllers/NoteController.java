package controller.controllers;

import model.communication.Note;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.communication.NoteDAO;
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

@ManagedBean(name = "noteController")
@SessionScoped
public class NoteController implements Serializable {

    private NoteDAO jpaController = null;
    private List<Note> items = null;
    private Note selected;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public NoteController() {
    }

    public Note getSelected() {
        return selected;
    }

    public void setSelected(Note selected) {
        this.selected = selected;
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

    private NoteDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new NoteDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Note prepareCreate() {
        selected = new Note();
        initializeEmbeddableKey();
        selected.setFrom(signInBean.getUser());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleNote").getString("NoteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleNote").getString("NoteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleNote").getString("NoteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Note> getItems() {
        if (signInBean.isStep1userSignedIn()) {
            items = getJpaController().findNotaEntities(signInBean.getUser());
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
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

    public List<Note> getItemsAvailableSelectMany() {
        return getJpaController().findNotaEntities();
    }

    public List<Note> getItemsAvailableSelectOne() {
        return getJpaController().findNotaEntities();
    }

    @FacesConverter(forClass = Note.class)
    public static class NotaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoteController controller = (NoteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noteController");
            return controller.getJpaController().findNota(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Note) {
                Note o = (Note) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Note.class.getName()});
                return null;
            }
        }
    }
}
