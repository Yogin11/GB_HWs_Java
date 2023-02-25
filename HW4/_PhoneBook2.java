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
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        /*
         * Реализовать телефонный справочник:
         * HashMap<Login, Список телефонов>
         * - добавлять телефон
         * - удалять телефон
         * - находить по логину телефон/список телефонов
         */
        // RecordsBook telephoneBook = new RecordsBook(null);
        int a = 0;
        Scanner request = new Scanner(System.in);
        // test = request.nextLine();
        List<List<StringBuilder>> telbook = fReader();

        menu();
        // System.out.print("\033[H\033[J");
        try {
            a = request.nextInt();
            // HashMap var = hashMethod(fReader());
            System.out.println();
            // choose(a, request, var);
            choose(a, request, telbook);

            // printAll(fReader());
            request.close();
        } catch (IOException e) {
            System.out.println("Неправильный ввод, попробуйте еще раз");
            System.out.println(e.getMessage());
        }
    }

    public static void menu() {
        System.out.printf("Выберите пункт: \n");
        String str1 = "1. Вывести весь список логин/телефон \n";
        String str11 = "11. Вывести весь тел. справочник \n";
        String str2 = "2. Добавить телефон \n"; // сначала поиск по логину
        String str3 = "3. Найти телефон/список телефонов по логину \n"; // reg expressions
        String str4 = "4. Удалить телефон \n"; // сначала поиск по логину
        String str5 = "5. Выход";
        // System.out.print("\033[H\033[J");
        System.out.printf(" %s %s %s %s %s \n", str1, str11, str2, str3, str4, str5);
        System.out.println();
    }

    public static void choose(int a, Scanner request, List<List<StringBuilder>> telbook) throws IOException {
        switch (a) {
            case 1:
                printLogTel(hashMethod(telbook));
                break;
            case 11:
                printAll(telbook);
                break;
            case 2:
                searchSubsHash(hashMethod(telbook));
                // addNumber();

                break;
            case 3:
                // searchSubsAll(fReader());
                searchSubsHash(hashMethod(telbook));
                System.out.println("Выберите номер записи");
                // int choice = request.nextInt();
                int choice = Integer.parseInt("2");

                break;
            case 4:
                break;
            case 5:
                request.close();
                System.exit(0);
                break;
            default:
                System.out.println("Выход");
                request.close();
                System.exit(0);
                break;
        }
    }

    public static void searchSubsAll(List<List<StringBuilder>> record) {
        String podstr = "mail";
        StringBuilder search = new StringBuilder("\\.*" + podstr + "\\.*");
        Pattern patt = Pattern.compile(search.toString());
        for (int i = 0; i < record.size(); i++) {
            Matcher match = patt.matcher(record.get(i).get(0).toString());
            // System.out.println("вот тут совпадение - " + match.group());
            while (match.find()) {
                // System.out.println("вот тут совпадение - " + match.group());
                System.out.println(record.get(i).get(5));
            }
        }
    }

    public static void searchSubsHash(HashMap<String, String[]> map1) {
        String podstr = ".ru";
        StringBuilder search = new StringBuilder("\\.*" + podstr + "\\.*");
        Pattern patt = Pattern.compile(search.toString());
        boolean found = false;
        System.out.println("----------------------------------------------------------------");
        System.out.println(String.format("   %-30s %10s", "Логин", "|      Телефон"));
        System.out.println("----------------------------------------------------------------");
        int numberMenu = 0;
        for (var i : map1.entrySet()) {
            Matcher match = patt.matcher(i.getKey());
            String stroka = "";
            while (match.find()) {
                found = true;
                for (int j = 0; j < i.getValue().length; j++) {
                    stroka += j > 0 ? ", " + i.getValue()[j] : i.getValue()[j];
                }
                numberMenu++;
                System.out.println(String.format("%d  %-30s |%10s", numberMenu, i.getKey(), stroka));
            }
        }
        if (!found) {
            System.out.println("Логин с таким набором символов не найден");
        }

    }

    public static List<List<StringBuilder>> fReader() throws IOException {
        String filename = "tel.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        String str;
        List<List<StringBuilder>> record = new ArrayList<>();
        RecordsBook telephoneBook = new RecordsBook(new ArrayList<>());

        while ((str = br.readLine()) != null) {
            String[] elements = str.split(",");
            StringBuilder[] strArr = new StringBuilder[elements.length];
            int i = 0;
            ArrayList<StringBuilder> phones = new ArrayList<StringBuilder>();
            for (String elem : elements) {
                if (i == 4) {
                    phones.add(elem);
                }else {
                StringBuilder variable = new StringBuilder(elem);
                strArr[i++] = variable;
                }
            }
            PhoneRecord zapis = new PhoneRecord(strArr[0], strArr[1], strArr[2], strArr[3], strArr[4]);
            // zapis.toOneRecord();
            telephoneBook.recordAdd(zapis.toOneRecord());
            // zapis.oneRecPrint();
            List<StringBuilder> detailsrecord = Arrays.asList(strArr);
            record.add(detailsrecord);
        }
        telephoneBook.printBook();
        br.close();
        return record;

    }

    public static HashMap<String, String[]> hashMethod(List<List<StringBuilder>> record) {
        HashMap<String, String[]> map1 = new HashMap<>();
        // StringBuilder input = new StringBuilder("Другая ФАМИЛИЯ");
        // StringBuilder search = new StringBuilder("Перелыгин");

        for (int i = 0; i < record.size(); i++) {
            int j = 0;
            String[] telvalues = new String[record.get(i).size() - 4];
            for (int k = 4; k < record.get(i).size(); k++) {
                telvalues[j++] = record.get(i).get(k).toString();
            }
            map1.put(record.get(i).get(0).toString(), telvalues);
        }
        // System.out.println();
        return map1;
    }

    public static void printLogTel(HashMap<String, String[]> map1) {
        for (var i : map1.entrySet()) {
            String stroka = "";
            for (int j = 0; j < i.getValue().length; j++) {
                stroka += j > 0 ? ", " + i.getValue()[j] : i.getValue()[j];
            }
            System.out.println(String.format("%-30s %10s", i.getKey(), stroka));

        }
    }

    public static void printAll(List<List<StringBuilder>> record) {
        HashMap<Integer, Integer> maxLen = new HashMap<>();
        int temp = 0;
        for (int i = 0; i < record.size(); i++) {
            for (int j = 0; j < record.get(i).size(); j++) {
                if (i == 0) {
                    maxLen.put(j, record.get(i).get(j).length() + 3);
                }
                if (maxLen.get(j) < record.get(i).get(j).length()) {
                    maxLen.put(j, record.get(i).get(j).length() + 2);
                }
            }
        }

        for (int i = 0; i < record.size(); i++) {
            int f = 0;
            String st = "|";
            for (StringBuilder entry : record.get(i)) {
                st = "%-" + maxLen.get(f++) + "s" + "| ";
                // System.out.println(st);
                System.out.printf(st, entry.toString());
            }
            System.out.println("");
        }

    }
}

class PhoneRecord {

    int num;
    int counter = 1;
    StringBuilder login;
    StringBuilder familyname;
    StringBuilder name;
    StringBuilder middlename;
    StringBuilder telephone;
    List<StringBuilder> oneRecord;

    PhoneRecord(StringBuilder login, StringBuilder familyname, StringBuilder name, StringBuilder middlename,
            StringBuilder telephone) {
        num = counter++;
        this.login = login;
        this.familyname = familyname;
        this.name = name;
        this.middlename = middlename;
        this.telephone = telephone;
    }

    public List<StringBuilder> toOneRecord() {
        this.oneRecord = new ArrayList<StringBuilder>();
        this.oneRecord.add(this.login);
        this.oneRecord.add(this.familyname);
        this.oneRecord.add(this.name);
        this.oneRecord.add(this.middlename);
        this.oneRecord.add(this.telephone);
        return oneRecord;
    }

    public void oneRecPrint() {
        System.out.println(" ВОТ ТУТ ПЕЧАТАЕТСЯ" + this.oneRecord);
    }



}
