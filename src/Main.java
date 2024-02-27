import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firstnumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondnumber = new Scanner(System.in).nextInt();
        int sum = firstnumber + secondnumber;
        int difference = secondnumber-firstnumber;
        int product = firstnumber*secondnumber;
        double quotient = firstnumber/secondnumber;
        System.out.println("Сумма введенных чисел: " + sum);
        System.out.println("Произведение введенных чисел: " + product);
        System.out.println("Разность введенных чисел: " + difference);
        System.out.println("Частное введеных чисел: " + quotient);
    }
}
