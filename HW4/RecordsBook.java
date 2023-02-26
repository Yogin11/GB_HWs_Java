import java.util.ArrayList;
import java.util.List;

public class RecordsBook {
    List<List<StringBuilder>> recordbook;
    
    public RecordsBook(List<List<StringBuilder>> recordbook) {
        this.recordbook = recordbook;

        // record = new ArrayList<>();
    }

  

    public void recordAdd(List<StringBuilder> var){
        this.recordbook.add(var);
        // this.recordbook.add() ;
    }

    public void printBook() {
        // System.out.println(this.recordbook);
        for (List<StringBuilder> list : this.recordbook) {
            System.out.println(" ВОТ ТУТ printBOOK" + list);

        }
    }
}