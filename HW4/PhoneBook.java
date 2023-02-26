import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        Scanner request = new Scanner(System.in);
        HashMap<String, String[]> telbook = fReader();
        // while (true) {
        int a = 0;
        boolean isExit = false;
        // test = request.nextLine();
        System.out.print("\033[H\033[J");
        while (!isExit) {

            menu();
            // System.out.print("\033[H\033[J");
            try {
                String s = request.nextLine();
                isExit = s.equals("exit");
                if (isExit) {
                    break;
                }
                a = Integer.parseInt(s);

                System.out.println();
                choose(a, request, telbook);
                // request.close();
            } catch (IOException e) {
                System.out.println("Неправильный ввод, попробуйте еще раз");
                System.out.println(e.getMessage());
            } finally {
                System.out.println();
            }
        }
        request.close();
    }

    public static void menu() {
        System.out.println("Выберите пункт: ");
        String str1 = "1. Вывести весь список логин/телефон \n";

        String str2 = "2. Найти телефон/список телефонов по логину \n"; // reg expressions
        String str3 = "3. Добавить телефон \n"; // сначала поиск по логину
        String str4 = "4. Удалить телефон \n"; // сначала поиск по логину
        String str5 = "5. Выход";

        System.out.printf(" %s %s %s %s %s \n", str1, str2, str3, str4, str5);
        System.out.println();
    }

    public static void choose(int a, Scanner request, HashMap<String, String[]> telbook) throws IOException {
        boolean update = false;
        String var = "";
        switch (a) {
            case 1:
                // 1. Вывести весь список логин/телефон
                printAll(telbook);
                // printLogTel(telbook);
                break;
            case 11:
                // printAll(telbook);
                break;
            case 2:
                // searchSubsAll(fReader());
                update = false;
                foundMenu(searchSubsHash(telbook, request), telbook, update, request);

                break;
            case 3:
                // 2. Добавить телефон
                // searchSubsHash(telbook);
                update = true;
                var = foundMenu(searchSubsHash(telbook, request), telbook, update, request);
                addNumber(var, telbook, request);
                break;
            case 4:
                // УДАЛИТЬ ТЕЛЕФОН
                update = true;
                var = foundMenu(searchSubsHash(telbook, request), telbook, update, request);

                removeNumber(var, telbook, request);

                break;
            case 5:
                request.close();
                System.exit(0);
                break;
            case 6:

                printAll(telbook);
                break;

            default:
                System.out.println("Выход");
                request.close();
                System.exit(0);
                break;
        }
    }

    public static void addNumber(String var, HashMap<String, String[]> telbook, Scanner request) {

        // String[] arr = var;
        System.out.printf("у пользователя с логином %s имеются след. телефоны: \n", var);

        List<String> listOfStrings = new ArrayList<String>();
        Collections.addAll(listOfStrings, telbook.get(var));
        int counter = 1;
        for (int i = 3; i < listOfStrings.size(); i++) {
            System.out.println(counter + " " + listOfStrings.get(i));
            counter++;
        }
        // System.out.println(listOfStrings);
        // String newnum = "+798568652323";
        System.out.println("Введите номер телефона: ");

        String newnum = input(request);

        listOfStrings.add(newnum);
        System.out.println(listOfStrings);
        // String[] per = (String[]) listOfStrings.toArray();
        String[] per = listOfStrings.toArray(new String[0]);

        telbook.put(var, per);
        System.out.println("Номер добавлен ");
        // printLogTel(telbook);

    }

    public static void removeNumber(String var, HashMap<String, String[]> telbook, Scanner request) {
        System.out.printf("у пользователя с логином %s имеются след. телефоны: \n", var);

        List<String> listOfStrings = new ArrayList<String>();
        Collections.addAll(listOfStrings, telbook.get(var));
        int counter = 1;
        for (int i = 3; i < listOfStrings.size(); i++) {
            System.out.println(counter + " " + listOfStrings.get(i));
            counter++;
        }

        // String choice = "1";
        System.out.println("Какой номер записи удалить? ");
        String choice = input(request);

        listOfStrings.remove(Integer.parseInt(choice) + 2);
        // System.out.println(listOfStrings);
        String[] per = listOfStrings.toArray(new String[0]);
        telbook.put(var, per);
        // printLogTel(telbook);
        System.out.println("Номер удален ");
    }

    public static String foundMenu(HashMap<String, String> found, HashMap<String, String[]> telbook, Boolean update,
            Scanner request) {

        if (update) {
            System.out.println("Выберите номер записи > ");
            String choice = input(request);
            return found.get(choice);
        } else {
            System.out.println(" ");

        }
        return null;
    }

    public static String input(Scanner request) {
        String a = " ";
        a = request.nextLine();
        System.out.println();
        return a;
    }

    public static void printRecord(String strLog, String[] data) {
        String stroka = "";
        for (String i : data) {
            stroka += i + " ";
        }
        System.out.println(String.format("%-20s %10s", strLog, stroka));
        System.out.println();
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

    public static HashMap<String, String> searchSubsHash(HashMap<String, String[]> map, Scanner request) {
        System.out.println("Введите символы из логина");

        // String podstr = ".com";
        String podstr = input(request);
        String findString = new String("\\.*" + podstr + "\\.*");
        Pattern patt = Pattern.compile(findString);
        HashMap<String, String> foundlist = new HashMap<>();

        HashMap<String, String[]> flist = new HashMap<>(); // TODODODODODODOODD

        boolean found = false;
        System.out.println("Найдены записи: ");
        
        // System.out.println("----------------------------------------------------------------");
        // System.out.println(String.format("№ |   %-30s %10s", "Логин", "|      Телефон"));
        // System.out.println("----------------------------------------------------------------");
        int numberMenu = 0;
        for (Entry<String, String[]> i : map.entrySet()) {
            Matcher match = patt.matcher(i.getKey());
            String stroka = "";
            while (match.find()) {

                found = true;
                flist.put(i.getKey(), i.getValue());
                for (int j = 0; j < i.getValue().length; j++) {
                    stroka += j > 0 ? ", " + i.getValue()[j] : i.getValue()[j];
                }
                numberMenu++;
                foundlist.put(Integer.toString(numberMenu), i.getKey());
                System.out.println(String.format("%d |  %-30s  | %10s", numberMenu, i.getKey(), stroka));
            }
        }
        printAll(flist);

        if (!found) {
            System.out.println("Логин с таким набором символов не найден");
        }
        // System.out.println(foundlist);
        return foundlist;
    }

    public static HashMap<String, String[]> fReader() throws IOException {
        String filename = "tel.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        String str;
        List<List<StringBuilder>> record = new ArrayList<>();
        RecordsBook telephoneBook = new RecordsBook(new ArrayList<>());

        HashMap<String, String[]> map2 = new HashMap<>();
        while ((str = br.readLine()) != null) {
            String[] elements = str.split(",");
            int j = 0;
            String[] telvalues = new String[elements.length - 1];
            for (int i = 1; i < elements.length; i++) {
                telvalues[j++] = elements[i];

            }
            map2.put(elements[0], telvalues);
            // printLogTel(map2);
            StringBuilder[] strArr = new StringBuilder[elements.length];
            int i = 0;
            ArrayList<StringBuilder> phones = new ArrayList<StringBuilder>();
            for (String elem : elements) {
                StringBuilder variable = new StringBuilder(elem);
                strArr[i++] = variable;
            }
            List<StringBuilder> detailsrecord = Arrays.asList(strArr);
            record.add(detailsrecord);
        }
        br.close();
        return map2;

    }

    public static HashMap<String, String[]> hashMethod(List<List<StringBuilder>> record) {
        HashMap<String, String[]> map1 = new HashMap<>();
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

    public static void printLogTel(HashMap<String, String[]> map) {
        for (Entry<String, String[]> i : map.entrySet()) {
            String stroka = "";
            for (int j = 0; j < i.getValue().length; j++) {
                stroka += j > 0 ? ", " + i.getValue()[j] : i.getValue()[j];
            }
            System.out.println(String.format("%-30s %10s", i.getKey(), stroka));
        }
    }

    public static void printAll(HashMap<String, String[]> records) {

        HashMap<Integer, Integer> maxLen = new HashMap<>();
        int temp = 0;
        int maxarrlen = 0;
        for (Entry<String, String[]> items : records.entrySet()) {
            if (temp == 0) {
                maxLen.put(0, items.getKey().length());
                for (int j = 0; j < items.getValue().length; j++) {
                    maxLen.put(j + 1, items.getValue()[j].length());
                    // System.out.println(j + 1 + " " + items.getValue()[j].length());
                }
                if (maxarrlen < items.getValue().length) {
                    maxarrlen = items.getValue().length;
                }
            } else {

                if (maxarrlen < items.getValue().length) {
                    maxarrlen = items.getValue().length;
                }
                if (maxLen.get(0) < items.getKey().length()) {
                    maxLen.put(0, items.getKey().length() + 3);
                    // System.out.println(0 + " " + items.getKey().length());
                }

                for (int j = 0; j < items.getValue().length; j++) {
                    if (maxLen.getOrDefault((j + 1), items.getValue()[j].length()) <= items.getValue()[j].length()) {
                        maxLen.put(j + 1, items.getValue()[j].length() + 3);
                    }
                    // System.out.println(j + 1 + " " + items.getValue()[j].length());
                }
            }

            temp++;
        }
        cover(maxLen);
        String[] columns = new String[] { "Login", "Фамилия", "Имя", "Отчество", "Телефоны" };
        int f = 0;
        String st = "";
        for (String item : columns) {
            st = "%-" + maxLen.get(f++) + "s" + "| ";
            System.out.printf(st, item);
        }
        System.out.println();
        cover(maxLen);
        

        for (Entry<String, String[]> items : records.entrySet()) {
            f = 1;
            st = "%-" + maxLen.get(0) + "s" + "| ";
            System.out.printf(st, items.getKey());
            for (int i = 0; i < maxarrlen; i++) {
                st = "%-" + maxLen.get(f++) + "s" + "| ";
                if (i >= items.getValue().length) {
                    System.out.printf(st, " ");
                } else
                    System.out.printf(st, items.getValue()[i]);

            }
            System.out.println();
        }
        cover(maxLen);
    }

    public static void cover(HashMap<Integer, Integer> maxLen) {
        int f = 0;
        String st = "";
        for (int i = 0; i < 7; i++) {
            st = "%-" + maxLen.get(f) + "s" + "| ";
            System.out.printf(st, "─".repeat(maxLen.get(f)));
            f++;
        }
        System.out.println();
    }
}

class PhoneRecord {

    int num;
    int counter = 1;
    String logs;
    String[] data;
    StringBuilder login;
    StringBuilder familyname;
    StringBuilder name;
    StringBuilder middlename;
    StringBuilder telephone;
    List<StringBuilder> oneRecord;

    PhoneRecord(String logs, String data[]) {
        this.logs = logs;
        this.data = data;

    }

    public void oneRecPrint() {
        System.out.println(" ВОТ ТУТ ПЕЧАТАЕТСЯ" + this.oneRecord);
    }

}
