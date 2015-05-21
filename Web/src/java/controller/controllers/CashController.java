package controller.controllers;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import controller.util.Font;
import model.finances.cash.Cash;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.dto.ConceptDTO;
import data.finances.cash.CashConceptDAO;
import data.finances.cash.CashDAO;
import data.finances.incomes.PaymentDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.IOException;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.servlet.http.HttpServletResponse;
import model.finances.cash.CashConcept;
import model.finances.incomes.Payment;
import model.util.DateTools;

@ManagedBean(name = "cashController")
@SessionScoped
public class CashController implements Serializable {

    private Cash selected;
    private List<Cash> items = null;
    private CashDAO jpaController = null;
    private CashConceptDAO conceptJpaController = null; 
    private PaymentDAO paymentJpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    private int weeksNumber;
    private int year;
    private int month;
    
    public CashController() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
    }

    public Cash getSelected() {
        return selected;
    }

    public void setSelected(Cash selected) {
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
    
    private CashDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CashDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }
    
    private CashConceptDAO getConceptJpaController() {
        if (conceptJpaController == null) {
            conceptJpaController = new CashConceptDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return conceptJpaController;
    }

    private PaymentDAO getPaymentJpaController() {
        if (paymentJpaController == null) {
            paymentJpaController = new PaymentDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return paymentJpaController;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public Cash prepareCreate() {
        selected = new Cash();
        selected.setFarm(((SignInController) JsfUtil.getSession().getAttribute("signInController")).getFarm());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCash").getString("CashCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCash").getString("CashUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCash").getString("CashDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
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
    
    public List<Cash> getItems() {
         if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findCashEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Finca");
            }
        }
        return items;
    }

    public List<Cash> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Cash> getItemsAvailableSelectOne() {
        return getItems();
    }

    @FacesConverter(forClass = Cash.class)
    public static class CashControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CashController controller = (CashController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cashController");
            return controller.getJpaController().findCash(getKey(value));
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
            if (object instanceof Cash) {
                Cash o = (Cash) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cash.class.getName());
            }
        }

    }
    
    public void generateReport(){
        FacesContext  facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "Flujo de Caja " + DateTools.getMonth(month) + " " + year + ".pdf" + "\"");
        Document pdf = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(pdf, response.getOutputStream());
            pdf.open();
            pdf.setPageSize(PageSize.A4);
            pdf.setMargins(15, 20, 15, 15);
            //HEADER
            pdf.add(header());
            pdf.add(Chunk.NEWLINE);
            
            //CONCEPTS (COSTS(?)) TABLE
            //9 - 10 columnas depende de las semanas en el mes
            pdf.add(new Paragraph("Conceptos Por Semana", Font.getFont(15)));
            pdf.add(conceptTable(year, month));
            pdf.add(Chunk.NEWLINE);
            
            //WEEKLY INCOMES' TABLE
            //6 - 7 columnas
            pdf.add(new Paragraph("Ingresos Por Semana", Font.getFont(15)));
            pdf.add(incomesTable(year, month));
            pdf.add(Chunk.NEWLINE);
            
            //INCOMES MINUS WEEKLY COSTS
            //6 - 7 columnas
            
            //CASH AND BANK
            //6 - 7 columnas
            
            //SUM SALES AND CASH
            //6 - 7 columnas
            
            pdf.close();
            facesContext.responseComplete();
        } catch (IOException ex) {
            Logger.getLogger(CashController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(CashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Element header(){
        Paragraph header = new Paragraph("Finca: " + permissionBean.getSignInBean().getFarm().getName() + "\n", Font.getFont(20));
        header.setAlignment(Element.ALIGN_CENTER);
        String title = "Reporte Flujo de Caja " + DateTools.getMonth(month) + " del " + year;
        String subTitle = selected.getName();
        Paragraph paragraph = new Paragraph(title, Font.getFont(20));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        header.add(paragraph);
        paragraph = new Paragraph(subTitle, Font.getFont(20));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        header.add(paragraph);
        header.setAlignment(Element.ALIGN_CENTER);
        return header;
    }
    
    private Element conceptTable(int year, int month){
        String[] headers = weeksHeader(year, month);
        PdfPTable table = new PdfPTable(weeksNumber + 4);
        PdfPCell cell;
       //Agregar encabezados de la tabla
        cell = new PdfPCell(new Phrase(" Concepto ", Font.getFont(10)));
        cell.setRowspan(2);
        //cell.setColspan(2);
        table.addCell(cell);
        for (int i = 0; i < weeksNumber; i++) {
            cell = new PdfPCell(new Phrase(headers[i], Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
        }
        String[] finalHeaders = {"Valor a Pagar", "SALDO", "Pago Total Mes"};
        for (int i = 0; i < finalHeaders.length; i++) {
            cell = new PdfPCell(new Phrase(finalHeaders[i], Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
        }
        
        //traer de ConceptCash con la caja seleccionada
        List<ConceptDTO> conceptsByWeeks = conceptsByWeeks(year, month);
        float[] totals = new float[weeksNumber];
        float totalPayable = 0;
        float totalMonth = 0;
        for(ConceptDTO coD : conceptsByWeeks){
            cell = new PdfPCell(new Phrase(coD.getName(), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            int index = 0;
            for(float f : coD.getValuesByWeek()){
                cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(f), Font.getFont(10)));
                cell.setRowspan(2);
                table.addCell(cell);
                totals[index] += f;
                index++;
            }
            cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(coD.getTotal()), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            totalPayable += coD.getTotal();
            cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(coD.getTotal()), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(coD.getTotal()), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            totalMonth += coD.getTotal();
        }
        cell = new PdfPCell(new Phrase("TOTAL", Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        System.out.println("TOTAL");
        for(float f : totals){
            cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(f), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            System.out.println("si " + f);
        }
        cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(totalPayable), Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        System.out.println("si To");
        cell = new PdfPCell(new Phrase("  ", Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        System.out.println("Si Vac");
        cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(totalMonth), Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        System.out.println("Si Ult");
        cell = new PdfPCell(new Phrase("Tiene huevo!!", Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        return table;
    }
    
    private List<ConceptDTO> conceptsByWeeks(int year, int month){
        Date lastDay = DateTools.getLastDay(year, month);
        Date monday = DateTools.getFirstMondayOfMonth(year, month);
        Date sunday;
        List<ConceptDTO> conceptsByWeeks = new ArrayList<ConceptDTO>();
        int week = 0;
        do{
            sunday = DateTools.getSunday(monday);
            List<CashConcept> conceptsWeek = sumConcepts(monday, sunday);
            for(CashConcept co : conceptsWeek){
                ConceptDTO previous = exitsConceptDTO(conceptsByWeeks, co.getDescription());
                if(previous == null){
                    ConceptDTO conceptDTO = new ConceptDTO(weeksNumber);
                    conceptDTO.setName(co.getDescription());
                    float[] values = conceptDTO.getValuesByWeek();
                    values[week] = co.getConceptValue();
                    conceptDTO.setValuesByWeek(values);
                    conceptsByWeeks.add(conceptDTO);
                }else{
                    float[] values = previous.getValuesByWeek();
                    values[week] = co.getConceptValue();
                    previous.setValuesByWeek(values);
                }
            }
            monday = DateTools.getNextMonday(sunday);
            week++;
        }while(sunday.before(lastDay));
        return conceptsByWeeks;
    }
    
    private List<CashConcept> sumConcepts(Date start, Date end){
        List<CashConcept> concepts = getConceptJpaController().findCashConceptEntities(selected, start, end, false);
        List<CashConcept> compactConcept = new ArrayList<CashConcept>();
        for(CashConcept concept : concepts){
            CashConcept co = exitsConcept(compactConcept,concept);
            if(co == null){
                concept.setConceptDate(start);
                compactConcept.add(concept);
            }else{
                co.setConceptValue(co.getConceptValue() + concept.getConceptValue());
            }
        }
        return compactConcept;
    }
    
    private ConceptDTO exitsConceptDTO(List<ConceptDTO> concepts, String name){
        for(ConceptDTO c : concepts){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }
    
    private CashConcept exitsConcept(List<CashConcept> concepts, CashConcept concept){
        for(CashConcept c : concepts){
            if(c.getDescription().equals(concept.getDescription())){
                return c;
            }
        }
        return null;
    }
    
    private Element incomesTable(int year, int month){
        String[] headers = weeksHeader(year, month);
        PdfPTable table = new PdfPTable(weeksNumber + 2);
        PdfPCell cell;
       //Agregar encabezados de la tabla
        cell = new PdfPCell(new Phrase(" Ingreso De ", Font.getFont(10)));
        cell.setRowspan(2);
        //cell.setColspan(2);
        table.addCell(cell);
        for (int i = 0; i < weeksNumber; i++) {
            cell = new PdfPCell(new Phrase(headers[i], Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
        }
        cell = new PdfPCell(new Phrase(" TOTAL MES ", Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        //traer de ConceptCash con la caja seleccionada
        List<ConceptDTO> paymentsByWeeks = paymentsByWeeks(year, month);
        float[] totals = new float[weeksNumber];
        float totalMonth = 0;
        for(ConceptDTO coD : paymentsByWeeks){
            cell = new PdfPCell(new Phrase(coD.getName(), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            int index = 0;
            for(float f : coD.getValuesByWeek()){
                cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(f), Font.getFont(10)));
                cell.setRowspan(2);
                table.addCell(cell);
                totals[index] += f;
                index++;
            }
            cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(coD.getTotal()), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
            totalMonth += coD.getTotal();
        }
        cell = new PdfPCell(new Phrase("TOTAL", Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        for(float f : totals){
            cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(f), Font.getFont(10)));
            cell.setRowspan(2);
            table.addCell(cell);
        }
        cell = new PdfPCell(new Phrase(new DecimalFormat("0.#").format(totalMonth), Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Tiene huevo!!", Font.getFont(10)));
        cell.setRowspan(2);
        table.addCell(cell);
        return table;
        
    }
    
    private List<ConceptDTO> paymentsByWeeks(int year, int month){
        Date lastDay = DateTools.getLastDay(year, month);
        Date monday = DateTools.getFirstMondayOfMonth(year, month);
        Date sunday;
        List<ConceptDTO> paymentsByWeeks = new ArrayList<ConceptDTO>();
        int week = 0;
        do{
            sunday = DateTools.getSunday(monday);
            List<Payment> paymentsWeek = sumPayments(monday, sunday);
            for(Payment pa : paymentsWeek){
                String clientName = pa.getCustomer().getName() + " " + pa.getCustomer().getLastName();
                ConceptDTO previous = exitsConceptDTO(paymentsByWeeks, clientName);
                if(previous == null){
                    ConceptDTO conceptDTO = new ConceptDTO(weeksNumber);
                    conceptDTO.setName(clientName);
                    float[] values = conceptDTO.getValuesByWeek();
                    values[week] = pa.getPaymentValue();
                    conceptDTO.setValuesByWeek(values);
                    paymentsByWeeks.add(conceptDTO);
                }else{
                    float[] values = previous.getValuesByWeek();
                    values[week] = pa.getPaymentValue();
                    previous.setValuesByWeek(values);
                }
            }
            monday = DateTools.getNextMonday(sunday);
            week++;
        }while(sunday.before(lastDay));
        return paymentsByWeeks;
    }
    
    private List<Payment> sumPayments(Date start, Date end){
        List<Payment> payments = getPaymentJpaController().findPaymentEntities(signInBean.getFarm(), start, end);
        List<Payment> compactPayments = new ArrayList<Payment>();
        for(Payment payment : payments){
            Payment co = exitsPayment(compactPayments, payment);
            if(co == null){
                payment.setPaymentDate(start);
                compactPayments.add(payment);
            }else{
                co.setPaymentValue(co.getPaymentValue() + payment.getPaymentValue());
            }
        }
        return compactPayments;
    }
    
    private Payment exitsPayment(List<Payment> payments, Payment payment){
        for(Payment pa : payments){
            if(pa.getCustomer().equals(payment.getCustomer())){
                return pa;
            }
        }
        return null;
    }
    
    private String[] weeksHeader(int year, int month){
        weeksNumber = 0;
        Date lastDay = DateTools.getLastDay(year, month);
        Date monday = DateTools.getFirstMondayOfMonth(year, month);
        Date sunday;
        String week = "";
        do{
            sunday = DateTools.getSunday(monday);
            week += "Del " + DateTools.getDay(monday) + " al " + DateTools.getDay(sunday) + " @ ";
            monday = DateTools.getNextMonday(sunday);
            weeksNumber++;
        }while(sunday.before(lastDay));
        return week.split("@");
    }
    
}
