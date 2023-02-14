public class dotsadd {
    public static void main(String[] args) {

        System.out.println("Задана фраза:");
        String initphrase = "  Добрый день        Как  дела   Все хорошо   ";
        System.out.println(initphrase);
        System.out.println("Полученная фраза:");
        System.out.println(dotsInsert(initphrase));
    }

    public static String dotsInsert(String initphrase) {
        String[] words = initphrase.trim().split("\\s*(\\s)");
        String isdot = "";
        for (int i = 0; i < words.length; i++) {
            char[] arr = words[i].toCharArray();
            if (arr[arr.length - 1] == '.') {
                isdot = words[i]; // Запоминаем, что точка уже стоит
            }
            if (Character.isUpperCase(arr[0]) && i != 0) {
                if (isdot.equals(words[i - 1])) // Проверка, стоит ли точка впереди уже
                    continue;
                else {
                    String str = words[i - 1] + ".";
                    words[i - 1] = str;
                }
            }
            if (i == words.length - 1 && arr[arr.length - 1] != '.') {
                String str = words[i] + ".";
                words[i] = str;
            }
        }
        String result = String.join(" ", words);
        return result;
    }
}