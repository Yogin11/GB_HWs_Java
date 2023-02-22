import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        /*
         * Реализовать телефонный справочник:
         * HashMap<Login, Список телефонов>
         * - добалять телефон
         * - удалять телефон
         * - находить по логину телефон/список телефонов
         */
        // StringBuilder str1 = new StringBuilder("Первое");
        // StringBuilder str2 = new StringBuilder("Второе");
        // StringBuilder str3 = new StringBuilder("Третье");

        // StringBuilder[] str = { str1, str2, str3 };
        // List<StringBuilder> detailsrecord = Arrays.asList(str);
        // List<List<StringBuilder>> record = new ArrayList<>();

        // record.add(detailsrecord);
        // ArrayList<String> detailsrecord1 = new ArrayList<>();
        // detailsrecord1.add("Четвертое");
        // detailsrecord1.add("Пятое");
        // detailsrecord1.add("Шестое");

        // record.add(detailsrecord1);

        // for (int i = 0; i < record.size(); i++) {
        // for (int j = 0; j < detailsrecord.size(); j++) {
        // System.out.printf("%s ",detailsrecord.get(j));

        // }
        // }
        // System.out.println(record);
        fReader();
    }

    public static void fReader() throws IOException {
        String filename = "tel.txt";
        // InputStreamReader fr = new InputStreamReader(new FileInputStream(filename, StandardCharsets.UTF_8));
        // FileReader fr = 
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        // new InputStreamReader(new FileInputStream(this.path, StandardCharsets.UTF_8))
        String str;
        List<List<StringBuilder>> record = new ArrayList<>();
        while ((str = br.readLine()) != null) {
            String elements[] = str.split(",");
            StringBuilder[] strArr = new StringBuilder[4];
            // System.out.println(strArr[0]);
            int i = 0;
            for (String elem : elements) {
                // System.out.println(elem);
                StringBuilder variable = new StringBuilder(elem);
                strArr[i++] = variable;
            }
            List<StringBuilder> detailsrecord = Arrays.asList(strArr);
            // System.out.println(detailsrecord);
            record.add(detailsrecord);
            // System.out.printf(" %s \n", str);
        }
        HashMap<String,String> map1 = new HashMap<>();
        StringBuilder input = new StringBuilder("Другая ФАМИЛИЯ");
        StringBuilder search = new StringBuilder("Перелыгин");
      
        for (int i = 0; i < record.size(); i++) {
            map1.put(record.get(i).get(0).toString(), record.get(i).get(3).toString());
            if (search.toString().equals(record.get(i).get(0).toString())) {

                System.out.println(record.get(i) + " " + search);


                record.get(i).set(0, input);
                System.out.println(record.get(i) + " " + input);
            }
        }
        System.out.println(record);
        System.out.println();
        // System.out.println(map1);

        for (Entry<String, String> i : map1.entrySet()) {
            System.out.println(i + " ");
            
        }
            
        
        br.close();
    }
    public static void hashMethod() {
        HashMap<Integer,String> map1 = new HashMap<>();
        
    }

}
