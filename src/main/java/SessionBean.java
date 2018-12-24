import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    private int inputInt;
   public int getInputInt() {
        return inputInt=inputInt+1;
    }
    public void setInputInt(int inputInt) {
          this.inputInt= inputInt+1;
        }
}
