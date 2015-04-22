package controller.controllers;

import model.administration.Farm;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.administration.FarmDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.Coordinate;
import model.administration.Department;
import model.administration.Municipality;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name = "farmController")
@SessionScoped
public class FarmController implements Serializable {

    private FarmDAO jpaController = null;
    private List<Farm> items = null;
    private Farm selected;
    private MapModel mapModel;
    private Marker marker;
    private Department department;
    private List<Municipality> municipalities;
    private int zoomLevel;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    @PostConstruct
    public void init() {
        mapModel = new DefaultMapModel();
        marker = new Marker(new LatLng(4.114974, -73.584086));
        zoomLevel = 5;
        if (selected != null) {
            marker = new Marker(new LatLng(selected.getCoordinate().getX(), selected.getCoordinate().getY()));
        }
        marker.setDraggable(true);
        mapModel.addOverlay(marker);
    }

    public Farm getSelected() {
        return selected;
    }

    public void setSelected(Farm selected) {
        this.selected = selected;
    }

    public SignInController getSignInBean() {
        return signInBean;
    }

    public void setSignInBean(SignInController signInBean) {
        this.signInBean = signInBean;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<Municipality> getMunicipalities() {
        municipalities = new MunicipalityController().getItems(department);
        return municipalities;
    }

    public void setMunicipalities(List<Municipality> municipalities) {
        this.municipalities = municipalities;
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void markerDragged(MarkerDragEvent evt) {
        marker = evt.getMarker();
        selected.setCoordinate(new Coordinate(marker.getLatlng().getLat(), marker.getLatlng().getLng()));
//        JsfUtil.addSuccessMessage("Marker Dragged" + "Lat:" + selected.getCoordenada().getX() + ", Lng:" + selected.getCoordenada().getY());
    }

    public void onPointSelect(PointSelectEvent event) {
        marker.setLatlng(event.getLatLng());
        selected.setCoordinate(new Coordinate(marker.getLatlng().getLat(), marker.getLatlng().getLng()));
    }
    
    public void onStateChange(StateChangeEvent event) {
        zoomLevel = event.getZoomLevel();
    }

    private FarmDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new FarmDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Farm prepareCreate() {
        selected = new Farm();
        selected.setCoordinate(new Coordinate(marker.getLatlng().getLat(), marker.getLatlng().getLng()));
        selected.setOwner(signInBean.getUser());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleFarm").getString("FarmCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleFarm").getString("FarmUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleFarm").getString("FarmDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Farm> getItems() {
        if (items == null) {
            items = getJpaController().findFarmEntitiesForCurrentUser(signInBean.getUser());
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

    public List<Farm> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Farm> getItemsAvailableSelectOne() {
        return getItems();
    }

    @FacesConverter(forClass = Farm.class)
    public static class FarmControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FarmController controller = (FarmController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "farmController");
            return controller.getJpaController().findFarm(getKey(value));
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
            if (object instanceof Farm) {
                Farm o = (Farm) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Farm.class.getName()});
                return null;
            }
        }
    }
}
