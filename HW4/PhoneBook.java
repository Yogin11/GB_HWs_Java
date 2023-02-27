import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    public static void main(String[] args) throws IOException,NumberFormatException  {
       
        Scanner request = new Scanner(System.in);
        HashMap<String, String[]> telbook = fReader();
        int a = 0;
        boolean isExit = false;
        System.out.print("\033[H\033[J");
        while (!isExit) {
            menu();
            try {
                String s = request.nextLine();
                isExit = s.equals("6");
                if (isExit) break;
                a = Integer.parseInt(s);
                System.out.println();
                choose(a, request, telbook);
            } catch (IOException e) {
                System.out.println("Неправильный ввод, попробуйте еще раз");
                System.out.println();
                // System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Неправильный ввод, попробуйте еще раз");
                System.out.println();
                // System.out.println(e.getMessage());
            } 
        }
        request.close();
    }

    public static void menu() {
        System.out.println("Выберите пункт: ");
        String str1 = "1. Вывести весь список \n";
        String str2 = "2. Найти телефон/список телефонов по логину \n"; // reg expressions
        String str3 = "3. Добавить телефон \n"; // сначала поиск по логину
        String str4 = "4. Удалить телефон \n"; // сначала поиск по логину
        String str5 = "5. Записать в файл  \n"; 
        String str6 = "6. Выход";
        System.out.printf(" %s %s %s %s %s %s\n", str1, str2, str3, str4, str5, str6);
        System.out.println();
    }

    public static void choose(int a, Scanner request, HashMap<String, String[]> telbook) throws IOException {
        boolean update = false;
        String var = "";
        switch (a) {
            case 1:
                // 1. Вывести весь список логин/телефон
                printAll(telbook);
                break;
            case 2:
                // Найти телефон/список телефонов по логину
                update = false;
                HashMap<String, String> temp = searchSubsHash(telbook, request);
                foundMenu(temp, telbook, update, request);
                break;
            case 3:
                // 3. Добавить телефон
                update = true;
                temp = searchSubsHash(telbook, request);
                if (!temp.isEmpty()) {
                    var = foundMenu(temp, telbook, update, request);
                    addNumber(var, telbook, request);
                }
                break;
            case 4:
                // 4 УДАЛИТЬ ТЕЛЕФОН
                update = true;
                temp = searchSubsHash(telbook, request);
                if (!temp.isEmpty()) {
                    var = foundMenu(temp, telbook, update, request);
                    removeNumber(var, telbook, request);
                }
                break;
            case 5:
                //запись в файл
                fWriter(telbook, request);
                break;   
            case 6:
            //выход
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

    public static String foundMenu(HashMap<String, String> found, HashMap<String, String[]> telbook, Boolean update,
            Scanner request) {
        if (update) {
            if (found.size() > 1) {
                System.out.println("Выберите номер записи > ");
                String choice = input(request);
                return found.get(choice);
            } else if (found.size() == 1) {
                return found.get("1");
            } else {
                return null;
            }
        } else {
            System.out.println(" ");
        }
        return null;
    }

    public static void addNumber(String var, HashMap<String, String[]> telbook, Scanner request) {
        List<String> listOfStrings = new ArrayList<String>();
        Collections.addAll(listOfStrings, telbook.get(var));
        int counter = 1;
        if (listOfStrings.size() > 3) {
            System.out.printf("у пользователя с логином %s имеются след. телефоны: \n", var);
            for (int i = 3; i < listOfStrings.size(); i++) {
                System.out.println(counter + ". " + listOfStrings.get(i));
                counter++;
            }
        }
        System.out.println("Введите номер телефона: ");
        String newnum = input(request);
        listOfStrings.add(newnum);
        String[] per = listOfStrings.toArray(new String[0]);
        telbook.put(var, per);
        System.out.println("Номер добавлен ");
        sleeper();
    }

    public static void removeNumber(String var, HashMap<String, String[]> telbook, Scanner request) {
        System.out.printf("у пользователя с логином %s имеются след. телефоны: \n", var);
        List<String> listOfStrings = new ArrayList<String>();
        Collections.addAll(listOfStrings, telbook.get(var));
        int counter = 0; 
        for (int i = 3; i < listOfStrings.size(); i++) {
            counter++;
            System.out.println(counter + ".   " + listOfStrings.get(i));
        }
        String choice = "";
        if (counter > 1) {
            System.out.println("Какой номер записи удалить? ");
            choice = input(request);
        } else
            choice = "1";
        System.out.println("Подтвердите удаление. y/n");
        String confirm = input(request);
        if (confirm.contains("y")) {
            listOfStrings.remove(Integer.parseInt(choice) + 2);
            String[] per = listOfStrings.toArray(new String[0]);
            telbook.put(var, per);
            System.out.println("Номер удален. ");
        } else
            System.out.println("Номер не удален.");
        sleeper();
    }

    public static void sleeper() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\033[H\033[J");
    }

    static String input(Scanner request) {
        String a = " ";
        a = request.nextLine();
        System.out.println();
        return a;
    }

    static HashMap<String, String> searchSubsHash(HashMap<String, String[]> map, Scanner request) {
        System.out.println("Введите символы из логина");
        String podstr = input(request);
        String findString = new String("\\.*" + podstr + "\\.*");
        Pattern patt = Pattern.compile(findString);
        HashMap<String, String> foundlist = new HashMap<>();
        HashMap<String, String[]> flist = new HashMap<>();
        boolean found = false;
        int numberMenu = 0;
        for (Entry<String, String[]> i : map.entrySet()) {
            Matcher match = patt.matcher(i.getKey());
            while (match.find()) {
                found = true;
                flist.put(i.getKey(), i.getValue());
                numberMenu++;
                foundlist.put(Integer.toString(numberMenu), i.getKey());
            }
        }
        if (!found) {
            System.out.println("Логин с таким набором символов не найден");
            return foundlist;
        } else
            System.out.println("Найдены записи: ");
        printAll(flist);
        return foundlist;
    }

    static HashMap<String, String[]> fReader() throws IOException {
        String filename = "tel.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        String str;
        HashMap<String, String[]> map2 = new HashMap<>();
        while ((str = br.readLine()) != null) {
            String[] elements = str.split(",");
            int j = 0;
            String[] telvalues = new String[elements.length - 1];
            for (int i = 1; i < elements.length; i++) {
                telvalues[j++] = elements[i];
            }
            map2.put(elements[0], telvalues);
        }
        br.close();
        return map2;
    }

    public static void fWriter(HashMap<String, String[]> map, Scanner request) {
        try {
            System.out.println("Введите название файла. Чтобы перезаписать текущий файл данных введите tel.txt: ");
            String filename = input(request);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            for (Entry<String, String[]> i : map.entrySet()) {
                bw.write(i.getKey());
                for (int j = 0; j < i.getValue().length; j++) {
                    bw.write(",");
                    bw.write(i.getValue()[j]);
                }
                bw.append('\n');
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
                }
                if (maxarrlen < items.getValue().length) {
                    maxarrlen = items.getValue().length;
                }
            } else {
                if (maxarrlen < items.getValue().length) {
                    maxarrlen = items.getValue().length;
                }
                if (maxLen.get(0) < items.getKey().length()) {
                    maxLen.put(0, items.getKey().length() + 2);
                }
                for (int j = 0; j < items.getValue().length; j++) {
                    if (maxLen.getOrDefault((j + 1), items.getValue()[j].length()) <= items.getValue()[j].length()) {
                        maxLen.put(j + 1, items.getValue()[j].length() + 2);
                    }
                }
            }
            temp++;
        }
        cover(maxLen);
        String[] columns = new String[] { "№", "Login", "Фамилия", "Имя", "Отчество", "Телефоны" };
        int f = 0;
        String st = "  |";
        System.out.print(columns[0] + "  |");
        for (String item : columns) {
            if (item == "№") {
                continue;
            }
            st = "%-" + maxLen.getOrDefault(f++, 10) + "s" + "| ";
            System.out.printf(st, item);
        }
        System.out.println();
        cover(maxLen);

        int counter = 1;
        for (Entry<String, String[]> items : records.entrySet()) {
            System.out.printf("%-2d |", counter);
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
            counter++;
        }
        cover(maxLen);
    }

    static void cover(HashMap<Integer, Integer> maxLen) {
        int f = 0;
        String st = "";
        System.out.print("   |");
        for (int i = 0; i < 7; i++) {
            st = "%-" + maxLen.getOrDefault(f, maxLen.get(2)) + "s" + "| ";
            System.out.printf(st, repeat(maxLen.getOrDefault(f, maxLen.get(2)), "─"));
            f++;
        }
        System.out.println();
    }

    static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }
}
