package controlador.controllers;

import modelo.produccion.administracion.Finca;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.administracion.FincaDAO;
import datos.util.EntityManagerFactorySingleton;
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
import modelo.produccion.administracion.Coordenada;
import modelo.produccion.administracion.Departamento;
import modelo.produccion.administracion.Municipio;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name = "fincaController")
@SessionScoped
public class FincaController implements Serializable {

    private FincaDAO jpaController = null;
    private List<Finca> items = null;
    private Finca selected;
    private MapModel modelo;
    private Marker marker;
    private Departamento departamento;
    private List<Municipio> municipios;
    private int zoomLevel;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    @PostConstruct
    public void init() {
        modelo = new DefaultMapModel();
        marker = new Marker(new LatLng(4.114974, -73.584086));
        zoomLevel = 5;
        if (selected != null) {
            marker = new Marker(new LatLng(selected.getCoordenada().getX(), selected.getCoordenada().getY()));
        }
        marker.setDraggable(true);
        modelo.addOverlay(marker);
    }

    public Finca getSelected() {
        return selected;
    }

    public void setSelected(Finca selected) {
        this.selected = selected;
    }

    public SignInController getSignInBean() {
        return signInBean;
    }

    public void setSignInBean(SignInController signInBean) {
        this.signInBean = signInBean;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
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

    public List<Municipio> getMunicipios() {
        municipios = new MunicipioController().getItems(departamento);
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public MapModel getModelo() {
        return modelo;
    }

    public void setModelo(MapModel modelo) {
        this.modelo = modelo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void markerDragged(MarkerDragEvent evt) {
        marker = evt.getMarker();
        selected.setCoordenada(new Coordenada(marker.getLatlng().getLat(), marker.getLatlng().getLng()));
//        JsfUtil.addSuccessMessage("Marker Dragged" + "Lat:" + selected.getCoordenada().getX() + ", Lng:" + selected.getCoordenada().getY());
    }

    public void onPointSelect(PointSelectEvent event) {
        marker.setLatlng(event.getLatLng());
        selected.setCoordenada(new Coordenada(marker.getLatlng().getLat(), marker.getLatlng().getLng()));
    }
    
    public void onStateChange(StateChangeEvent event) {
        zoomLevel = event.getZoomLevel();
    }

    private FincaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new FincaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Finca prepareCreate() {
        selected = new Finca();
        selected.setCoordenada(new Coordenada(marker.getLatlng().getLat(), marker.getLatlng().getLng()));
        selected.setPropietario(signInBean.getUser());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FincaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FincaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FincaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Finca> getItems() {
        if (items == null) {
            items = getJpaController().findFincaEntitiesForCurrentUser(signInBean.getUser());
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!permisoBean.currentUserHasPermission(persistAction, selected.getClass())) {
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

    public List<Finca> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Finca> getItemsAvailableSelectOne() {
        return getItems();
    }

    @FacesConverter(forClass = Finca.class)
    public static class FincaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FincaController controller = (FincaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fincaController");
            return controller.getJpaController().findFinca(getKey(value));
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
            if (object instanceof Finca) {
                Finca o = (Finca) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Finca.class.getName()});
                return null;
            }
        }
    }
}
