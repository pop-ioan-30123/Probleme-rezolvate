/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ionic
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Citim punctele din input
        Point[] points = parseInput(args);

        // Numărăm dreptunghiurile
        int count = countRectangles(points);

        // Afișăm numărul de dreptunghiuri
        System.out.println(count);
    }

    // Metoda care parsează inputul și creează un array de obiecte de tipul Point
    static Point[] parseInput(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Verificăm dacă există un argument la rularea programului și încercăm să îl folosim ca fișier de input
        if (args.length > 0) {
            try {
                scanner = new Scanner(args[0]);
            } catch (Exception e) {
                System.err.println("Calea fișierului este invalidă: " + args[0]);
                System.exit(1);
            }
        }

        // Citim inputul ca un șir de caractere și îl împărțim în linii
        String input = scanner.useDelimiter("\\A").next();
        String[] lines = input.trim().split("\n");

        // Parcurgem fiecare linie, împărțim coordonatele folosind separatorul "," și creăm un obiect de tipul Point
        Point[] points = new Point[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].trim().split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            points[i] = new Point(x, y);
        }

        return points;
    }

    // Metoda care numără dreptunghiurile formate dintr-un array de puncte
    static int countRectangles(Point[] points) {
        int count = 0;

        // Parcurgem fiecare pereche de puncte
        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point p2 = points[j];

                // Verificăm dacă cele două puncte formează un dreptunghi paralel cu axele X și Y
                if (p1.x != p2.x && p1.y != p2.y) {
                    // Creăm două puncte suplimentare pentru a verifica dacă acestea există în array-ul de puncte
                    Point p3 = new Point(p1.x, p2.y);
                    Point p4 = new Point(p2.x, p1.y);

                    // Verificăm dacă cele două puncte suplimentare există în array-ul de puncte și incrementăm numărul de dreptunghiuri dacă da
                    if (containsPoint(points, p3) && containsPoint(points, p4)) {
                        count++;
                    }
                }
            }
        }

        // Împărțim numărul de dreptunghiuri la 2, deoarece am numărat fiecare dreptunghi de două ori (o dată pentru fiecare pereche de puncte)
        return count / 2;
    }

    static boolean containsPoint(Point[] points, Point p) {
        // Parcurgem fiecare punct din array
        for (Point point : points) {
            // Verificăm dacă coordonatele punctului din array coincid cu cele ale punctului dat ca argument
            if (point.x == p.x && point.y == p.y) {
                // Returnăm adevărat dacă găsim un punct care se potrivește cu cel dat ca argument
                return true;
            }
        }
        // Dacă ajungem la sfârșitul array-ului și nu am găsit niciun punct care să se potrivească cu cel dat ca argument, returnăm fals
        return false;
    }

    // Clasa Point de coordonate x si y
    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
