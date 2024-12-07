package API;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String test;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter:");
        while (sc.hasNextLine()) {
            test = sc.nextLine();
            String answer = AI.getFreeAIResponse(test);
            System.out.println(answer);
        }
    }
}