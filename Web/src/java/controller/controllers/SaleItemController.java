package controller.controllers;

import model.finances.incomes.SaleItem;
import controller.util.JsfUtil;
import data.finances.incomes.SaleItemDAO;
import data.util.EntityManagerFactorySingleton;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "saleItemController")
@SessionScoped
public class SaleItemController implements Serializable {

    private SaleItem selected;
    private DataModel items = null;
    private SaleItemDAO jpaController = null;
    private int selectedItemIndex;

    public SaleItemController() {
    }

    public SaleItem getSelected() {
        if (selected == null) {
            selected = new SaleItem();
            selectedItemIndex = -1;
        }
        return selected;
    }

    private SaleItemDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new SaleItemDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public String prepareCreate() {
        selected = new SaleItem();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getJpaController().create(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleSaleItem").getString("SaleItemCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleSaleItem").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        selected = (SaleItem) getItems().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getJpaController().edit(selected);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleSaleItem").getString("SaleItemUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleSaleItem").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        selected = (SaleItem) getItems().getRowData();
        performDestroy();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getJpaController().destroy(selected.getId());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/BundleSaleItem").getString("SaleItemDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/BundleSaleItem").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getJpaController().getSaleItemCount();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            
        }
        if (selectedItemIndex >= 0) {
            selected = getJpaController().findSaleItemEntities(1, selectedItemIndex).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findSaleItemEntities(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findSaleItemEntities(), true);
    }

    @FacesConverter(forClass = SaleItem.class)
    public static class SaleItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SaleItemController controller = (SaleItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "saleItemController");
            return controller.getJpaController().findSaleItem(getKey(value));
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
            if (object instanceof SaleItem) {
                SaleItem o = (SaleItem) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SaleItem.class.getName());
            }
        }

    }

}
