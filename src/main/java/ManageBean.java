import app.entities.Animal;
import app.model.DAO;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.Iterator;

@ManagedBean(name = "manageBean")
@RequestScoped
public class ManageBean {
    private Date inputDate;
    private String inputText;
    private int inputInt;
    private org.apache.myfaces.trinidad.component.UIXTable table;
    public org.apache.myfaces.trinidad.component.UIXTable getTable() {
        return table;
    }
    public void setTable(org.apache.myfaces.trinidad.component.UIXTable table) {
        this.table = table;
    }
    public String getInputText() {
        return inputText;
    }
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
    public Date getInputDate() {
        return inputDate;
    }
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
    public int getInputInt() {
        return inputInt;
    }
     public void setInputInt(int inputInt) {
        try {
            int conversionFactor = 39;
            int inches = conversionFactor * inputInt;
            this.inputInt = inches;
        }
        catch (NumberFormatException e) {

        }
    }
    public String delete() {
// получение списка выбранных строк
        Iterator selection = table.getSelectedRowKeys().iterator();
// запоминание текущей строки таблицы
        Object oldKey = table.getRowKey();
// цикл по списку выбранных строк
        while (selection.hasNext()) {
            try {
                Object rowKey = selection.next();
// установка текущей строки таблицы
                table.setRowKey(rowKey);
// получение данных из текущей строки
                Animal row = (Animal) table.getRowData();
// удаление клиента
                DAO DAO =new DAO();
                DAO.deleteAnimal(row);
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Deletion failed", ex.getLocalizedMessage()));
            }
        }
// восстановление запомненной текущей строки
        table.setRowKey(oldKey);
// возврат строки результата
        return "refresh";
    }

}

