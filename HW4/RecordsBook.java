import java.util.ArrayList;
import java.util.List;

public class RecordsBook {
    List<List<String>> recordbook;
    
    public RecordsBook(List<List<String>> recordbook) {
        this.recordbook = recordbook;

        // record = new ArrayList<>();
    }

  

    public void recordAdd(List<String> var){
        this.recordbook.add(var);
        // this.recordbook.add() ;
    }

    public void printBook() {
        // System.out.println(this.recordbook);
        for (List<String> list : this.recordbook) {
            System.out.println(" ВОТ ТУТ printBOOK" + list);

        }
    }
}