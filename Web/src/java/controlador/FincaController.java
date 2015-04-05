package controlador;

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
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.produccion.administracion.Contrato;
import modelo.produccion.administracion.Coordenada;
import modelo.produccion.administracion.Departamento;
import modelo.produccion.administracion.Municipio;
import modelo.produccion.administracion.Persona;
import org.primefaces.event.map.MarkerDragEvent;
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

    @PostConstruct
    public void init() {
        modelo = new DefaultMapModel();
        marker = new Marker(new LatLng(4.114974, -73.584086));
        if (selected != null) {
            marker = new Marker(new LatLng(selected.getCoordenada().getX(), selected.getCoordenada().getY()));
        }
        marker.setDraggable(true);
        modelo.addOverlay(marker);
    }

    public FincaController() {
    }

    public Finca getSelected() {
        return selected;
    }

    public void setSelected(Finca selected) {
        this.selected = selected;
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
        JsfUtil.addSuccessMessage("Marker Dragged" + "Lat:" + selected.getCoordenada().getX() + ", Lng:" + selected.getCoordenada().getY());
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
        selected.setPropietario(((SignInController) JsfUtil.getSession().getAttribute("signInController")).getUser());
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
            items = getJpaController().findFincaEntities();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!new PermisoController().tienePermiso(persistAction, selected.getClass())) {
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
        return getJpaController().findFincaEntities();
    }

    public List<Finca> getItemsAvailableSelectOne() {
        return getJpaController().findFincaEntities();
    }

    List<Finca> getItems(Persona user) {
        List<Finca> fincas = getJpaController().findFincaEntities(user);
        for (Contrato c : new ContratoController().getItems(user)) {
            if (!fincas.contains(c.getFinca())) {
                fincas.add(c.getFinca());
            }
        }
        return fincas;
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
