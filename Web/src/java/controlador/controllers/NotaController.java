package controlador.controllers;

import model.communication.Note;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.utilidades.NotaDAO;
import datos.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

@ManagedBean(name = "notaController")
@SessionScoped
public class NotaController implements Serializable {

    private NotaDAO jpaController = null;
    private List<Note> items = null;
    private Note selected;
    private Dashboard dashboard;
    private DashboardModel dashboardModel;
    private final int columnCount = 3;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        dashboard = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        dashboard.setId("dashboard");

        createDasboardModel();
    }

    public Note getSelected() {
        return selected;
    }

    public void setSelected(Note selected) {
        this.selected = selected;
    }

    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }

    public SignInController getSignInBean() {
        return signInBean;
    }

    public void setSignInBean(SignInController signInBean) {
        this.signInBean = signInBean;
    }

    private void addChildren() {
        dashboard.getChildren().clear();
        createDasboardModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();
        List<Note> items1 = getItems();
        for (int i = 0; i < items1.size(); i++) {
            Note nota = items1.get(i);
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("nota_" + items1.get(i).getId());
            panel.setHeader(items1.get(i).getTitle());
            panel.setClosable(true);
            panel.setToggleable(true);

            dashboard.getChildren().add(panel);
            DashboardColumn column = dashboardModel.getColumn(i % columnCount);
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue(nota.getFrom().toString());
            panel.getChildren().add(text);
            HtmlOutputText text2 = new HtmlOutputText();
            text2.setValue(nota.getMessage());
            panel.getChildren().add(text2);
        }
        RequestContext.getCurrentInstance().update(":NotaListForm:notaDashboard");
    }

    public Dashboard getDashboard() {
        addChildren();
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NotaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new NotaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Note prepareCreate() {
        selected = new Note();
        initializeEmbeddableKey();
        HttpSession session = JsfUtil.getSession();
        SignInController loginBean = (SignInController) session.getAttribute("signInController");
        selected.setFrom(loginBean.getUser());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NotaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NotaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NotaDeleted"));
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

    private void createDasboardModel() {
        dashboardModel = new DefaultDashboardModel();
        for (int i = 0, n = columnCount; i < n; i++) {
            DashboardColumn column = new DefaultDashboardColumn();
            dashboardModel.addColumn(column);
        }
        dashboard.setModel(dashboardModel);
    }

    @FacesConverter(forClass = Note.class)
    public static class NotaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotaController controller = (NotaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notaController");
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
