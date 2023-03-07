import java.util.ArrayList;
// import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        getArray(array);
        TreeNode root = fillTree(array);
        root.printOutOrder(root.preorderTraversal(root));
    }

    
    /**
     * Запрос элементов у пользователя и формирование из них массива
     * @param array массив элементов
     */
    public static void getArray(ArrayList<Integer> array) {
        // example: 1,5,null,null,2,3,null,null,15,null,20,null,null,22
        Integer a = 0;
        String s = "";
        Scanner sc = new Scanner(System.in);
        // Проверки на ошибки ввода нет!!.
        System.out.println("Введите число массив узлов(для окончания ввода наберите 777): ");
        boolean isExit = false;
        while (!isExit) {
            s = sc.nextLine();
            isExit = s.equals("777");
            if (isExit)
                break;
            if (s.equals("null")) {
                array.add(null);
            } else {
                a = Integer.parseInt(s);
                array.add(a);
            }
        }
        sc.close();
        System.out.println("Введенный массив узлов:" + array);
    }

     /**
     * Заполнение дерева элементами из массива
     * @param array сформированный массив значений узлов дерева
     * @return дерево со значениями
    * 
    * */
    public static TreeNode fillTree(ArrayList<Integer> array) {
        if (!array.isEmpty()) {
            if (array.size() == 1 && array.get(0) != null) {
                int tmp = array.get(0);
                array.remove(0);
                return new TreeNode(tmp);
            } else if (array.get(0) == null) {
                array.remove(0);
                return new TreeNode();
            } else {
                int temp = array.get(0);
                array.remove(0);
                return new TreeNode(temp, fillTree(array), fillTree(array));
            }
        }
        return null;
    }
}