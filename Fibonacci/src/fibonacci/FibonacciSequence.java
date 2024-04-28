package fibonacci;

import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciSequence {

    private String result;
    private int position;
    private ArrayList<Integer>[] fiboNumbers;
    private int carry;
    private Scanner scanner;

    public FibonacciSequence() {
        this.result = "";
        this.position = 0;
        this.fiboNumbers = new ArrayList[3];
        this.fiboNumbers[0] = new ArrayList<>();
        this.fiboNumbers[0].add(1);
        this.fiboNumbers[1] = new ArrayList<>();
        this.fiboNumbers[1].add(1);
        this.fiboNumbers[2] = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.carry = 0;
    }

    public void enterPosition() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Ingresa la posición: ");
            try {
                this.position = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Error: Por favor, ingresa solo números enteros.");
                scanner.next();
            }
        }
    }

    public int separateTheCarryFromTheNumber(Integer num) {
        String auxiliar = num.toString();
        if (auxiliar.length() > 9) {
            num = Integer.parseInt(auxiliar.substring(1, auxiliar.length()));
            carry = Integer.parseInt(auxiliar.substring(0, 1));
        } else {
            carry = 0;
        }
        return num;
    }

    public ArrayList<Integer> addLists() {
        int indexNodo = 0;
        ArrayList<Integer> number = new ArrayList<>(fiboNumbers[1]);
        do {
            number.set(indexNodo, separateTheCarryFromTheNumber(carry + fiboNumbers[0].get(indexNodo) + fiboNumbers[1].get(indexNodo)));
            indexNodo++;
        } while (indexNodo < fiboNumbers[0].size());

        while (indexNodo < fiboNumbers[1].size()) {
            number.set(indexNodo, separateTheCarryFromTheNumber(carry + fiboNumbers[1].get(indexNodo)));
            indexNodo++;
        }
        if (carry != 0) {
            number.add(carry);
            carry = 0;
        }
        return number;
    }

    public void writeResult() {
        for (int i = fiboNumbers[2].size() - 1; i >= 0; i--) {
            String numberString = fiboNumbers[2].get(i).toString();
            if (i != fiboNumbers[2].size() - 1) {
                while (numberString.length() < 9) {
                    numberString = "0" + numberString;
                }
            }
            result += numberString;
        }
        System.out.println("El numero de la serie fibonacci en la posicion " + position + " es: " + result);
        result = "";
    }

    public void numberOfFibonacciInPosition() {
        if (position == 0) {
            System.out.println("El numero de la serie fibonacci en la posicion " + position + " es: 0");
        } else if (position == 1) {
            System.out.println("El numero de la serie fibonacci en la posicion " + position + " es: 1");
        } else {
            for (int i = 2; i <= position; i++) {
                fiboNumbers[2] = addLists();
                fiboNumbers[0] = new ArrayList<>(fiboNumbers[1]);
                fiboNumbers[1] = new ArrayList<>(fiboNumbers[2]);
            }
            writeResult();
        }
    }
}