package controller.controllers;

import controller.util.JsfUtil;
import model.finances.incomes.Payment;
import data.finances.incomes.PaymentDAO;
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

@ManagedBean(name = "paymentController")
@SessionScoped
public class PaymentController implements Serializable {

    private Payment selected;
    private List<Payment> items = null;
    private PaymentDAO jpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public PaymentController() {
    }

    public Payment getSelected() {
        return selected;
    }

    public void setSelected(Payment selected) {
        this.selected = selected;
    }

    private PaymentDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PaymentDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
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
    
    public void prepareCreate() {
        selected = new Payment();
        if (signInBean.getFarm() != null) {
            selected.setFarm(signInBean.getFarm());
            initializeEmbeddableKey();
        } else {
            JsfUtil.addErrorMessage("Seleccione una finca");
            selected = null;
        }
    }

    public void create() {
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/BundlePayment").getString("PaymentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePayment").getString("PaymentUpdated"));
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/BundlePayment").getString("PaymentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!getPermissionBean().currentUserHasPermission(persistAction, selected.getClass())) {
                return;
            }
            try {
                if (persistAction == JsfUtil.PersistAction.UPDATE) {
                    getJpaController().edit(selected);
                } else if (persistAction == JsfUtil.PersistAction.CREATE) {
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

    public List<Payment> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findPaymentEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una finca");
            }
        }
        return items;
    }

    public List<Payment> getItemsAvailableSelectMany() {
        return getJpaController().findPaymentEntities();
    }

    public List<Payment> getItemsAvailableSelectOne() {
        return getJpaController().findPaymentEntities();
    }

    @FacesConverter(forClass = Payment.class)
    public static class PaymentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaymentController controller = (PaymentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paymentController");
            return controller.getJpaController().findPayment(getKey(value));
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
            if (object instanceof Payment) {
                Payment o = (Payment) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Payment.class.getName());
            }
        }

    }

}
