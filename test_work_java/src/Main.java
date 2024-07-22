import java.io.IOException;
import java.util.Scanner;
//Almazbekova Bermet
class Main {
    //собственное исключение
    static class UnCorrectInput extends Exception {
        public UnCorrectInput(String message) {
            super(message);
        }
    }

    public static String calc(String input) throws UnCorrectInput, Exception {
        // Убираем пробелы в строке
        String newStr = input.replaceAll(" ", "");
        System.out.print("вывод строки без пробелов: ");
        System.out.println(newStr);

        // Проверка на пустую строку
        if (newStr.isEmpty()) {
            throw new UnCorrectInput("Некорректный формат строки. Отсутствуют операнды и оператор.");
        }

        // Перевод строки в массив символов
        char[] charArray = newStr.toCharArray();

        // Находим позицию оператора
        int operatorIndex = -1;
        char operator = ' ';
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '+' || charArray[i] == '-' || charArray[i] == '*' || charArray[i] == '/' || charArray[i] == '%') {
                if (operatorIndex != -1) { // Если уже найден оператор
                    throw new UnCorrectInput("Некорректный формат строки. Обнаружено несколько операторов.");
                }
                operator = charArray[i];
                operatorIndex = i;
            }
        }

        // Проверка на наличие оператора
        if (operatorIndex == -1) {
            throw new UnCorrectInput("Некорректный формат строки.");
        }

        // Парсинг операндов
        int num1;
        int num2;
        try {
            num1 = Integer.parseInt(newStr.substring(0, operatorIndex));
            num2 = Integer.parseInt(newStr.substring(operatorIndex + 1));
        } catch (NumberFormatException e) {
            throw new UnCorrectInput("Ошибка. Вводите  целые числа.");
        }

        // Проверка диапазона чисел
        if (num1 < 0 || num1 > 10 || num2 < 0 || num2 > 10) {
            throw new IllegalArgumentException("Операнды должны быть числами от 0 до 10.");
        }

        // Выполнение операции в зависимости от оператора
        int answer = 0;
        switch (operator) {
            case '-':
                answer = num1 - num2;
                break;
            case '+':
                answer = num1 + num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("На ноль деление невозможно.");
                }
                answer = num1 / num2;
                break;
            case '*':
                answer = num1 * num2;
                break;
            case '%':
                answer = num1 % num2;
                break;
            default:
                throw new IOException("Некорректный оператор.");
        }

        return String.valueOf(answer);
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Input: ");
            String str = in.nextLine();
            try {
                String result = calc(str);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
