package ec.edu.espoch.yasuniexplora;

import ec.edu.espoch.yasuniexplora.daointerface.DeviceDAO;
import ec.edu.espoch.yasuniexplora.daointerface.GuideDAO;
import ec.edu.espoch.yasuniexplora.daointerface.TourDAO;
import ec.edu.espoch.yasuniexplora.datasource.DeviceDAOImpl;
import ec.edu.espoch.yasuniexplora.datasource.GuideDAOImpl;
import ec.edu.espoch.yasuniexplora.datasource.TourDAOImpl;
import ec.edu.espoch.yasuniexplora.objects.*;
import java.util.Scanner;

public class YasuniExplora {

    public static void main(String[] args) {
        TourDAO tourDAO = new TourDAOImpl(10);
        DeviceDAO deviceDAO = new DeviceDAOImpl(10);
        GuideDAO guideDAO = new GuideDAOImpl(10);
        TourPlanner planner = new TourPlanner(tourDAO, deviceDAO, guideDAO);

        // 1. Registro de recursos (dispositivos y guías)
        deviceDAO.save(new CommunicationDevice("DEV-01", "Radio Satelital Garmin", true));
        deviceDAO.save(new CommunicationDevice("DEV-02", "Radio VHF Largo Alcance", true));

        guideDAO.save(new Guide("0601234567", "Daniela Morales", true));
        guideDAO.save(new Guide("0609876543", "Carlos Alvarado", true));

        // 2. Creación de tours y senderos
        RegularTour tour1 = new RegularTour("T-101", "Aventura Bosque Primario");
        tour1.addTrail(new Trail("Sendero Anaconda", 4.5, 60, true));
        tour1.addTrail(new Trail("Sendero Ceibo", 2.0, 30, true));

        InterpretiveTour tour2 = new InterpretiveTour("T-102", "Ruta Científica Yasuní", 45);
        tour2.addTrail(new Trail("Sendero Laguna Añangu", 5.0, 75, true));
        tour2.addTrail(new Trail("Sendero Saladero de Loros", 1.5, 25, true));

        tourDAO.save(tour1);
        tourDAO.save(tour2);

        // 3. Asignación de recursos (aceptada y rechazada)
        System.out.println("=== ASIGNACIÓN DE RECURSOS ===");
        boolean ok1 = planner.assignResources("T-101", "DEV-01", "0601234567");
        System.out.println("Asignación T-101: " + (ok1 ? "ACEPTADA" : "RECHAZADA"));

        boolean ok2 = planner.assignResources("T-102", "DEV-01", "0609876543");
        System.out.println("Asignación T-102 (con DEV-01 ocupado): " + (ok2 ? "ACEPTADA" : "RECHAZADA"));

        boolean ok3 = planner.assignResources("T-102", "DEV-02", "0609876543");
        System.out.println("Asignación T-102 (recursos libres): " + (ok3 ? "ACEPTADA" : "RECHAZADA"));

        // 4. Ordenamiento por criterio (DISTANCE y DURATION)
        System.out.println(" TOURS ORDENADOS POR DISTANCIA ");
        planner.setCriterion(OrderCriterion.DISTANCE);
        printTours(planner.orderTours());

        System.out.println(" TOURS ORDENADOS POR DURACIÓN TOTAL ");
        planner.setCriterion(OrderCriterion.DURATION);
        printTours(planner.orderTours());

        // 5. Simulación de incremento del factor de dificultad
        System.out.println("SIMULACIÓN DE FACTOR DE DIFICULTAD ");
        Scanner sc = new Scanner(System.in);
        double simulated = planner.simulateDifficulty(tour1.getDifficultyFactor());
        System.out.printf("Factor actual: %.2f | Factor simulado: %.2f%n", tour1.getDifficultyFactor(), simulated);
        System.out.print("¿Desea aplicar este nuevo factor al tour " + tour1.getCode() + "? (s/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            tour1.setDifficultyFactor(simulated);
            System.out.printf("Nuevo factor aplicado. Nueva duración total de %s: %.2f min%n",
                    tour1.getName(), tour1.getTotalDuration());
        } else {
            System.out.println("Simulación descartada. Se mantiene el factor original.");
        }

        // 6. Reporte de cierre de un sendero
        System.out.println(" CIERRE DE SENDERO ");
        boolean closed = planner.closeTrail(tour1, "Sendero Ceibo");
        System.out.println("Cierre de 'Sendero Ceibo' en " + tour1.getCode() + ": " + (closed ? "REALIZADO" : "NO ENCONTRADO"));
        System.out.printf("Tour %s actualizado -> Distancia: %.2f km | Duración: %.2f min%n",
                tour1.getName(), tour1.getTotalDistance(), tour1.getTotalDuration());
    }

    private static void printTours(Tour[] tours) {
        for (Tour t : tours) {
            System.out.printf("[%s] %s (%s) -> Distancia: %.2f km | Duración: %.2f min | Asignado: %s%n",
                    t.getCode(), t.getName(), t.getType(),
                    t.getTotalDistance(), t.getTotalDuration(),
                    t.isAssigned() ? "Sí" : "No");
        }
    }
}
