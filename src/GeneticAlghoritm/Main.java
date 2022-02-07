package GeneticAlghoritm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("To see the effects of the genetic algorithm, select the appropriate data file\nEnter the name of the file:");
            String fileName = scanner.next();
            System.out.print("Enter the probability of mutation (the best results are for 0,01): ");
            double mutationChance = scanner.nextDouble();
            System.out.print("Enter the probability of crossover (the best results are for 0,9): ");
            double crossoverChance = scanner.nextDouble();
            System.out.print("Enter the size of the solution population: ");
            int sizeOfPopulation = scanner.nextInt();
            System.out.print("How many iterations of the algorithm you want to perform?\nOne iteration is crossing and mutating the entire population\nEnter the number: ");
            int iterations = scanner.nextInt();

            CGAOptymalizer cgaOptimizer1 = new CGAOptymalizer(mutationChance, crossoverChance);
            cgaOptimizer1.loadInstance(fileName);
            cgaOptimizer1.initialize(sizeOfPopulation);

            System.out.println("Maximum fulfillment of the clauses before the genetic algorithm: ");
            System.out.println(cgaOptimizer1.findBestResult() + "/" + cgaOptimizer1.getcMax3SatProblem().getClauses().size() / 3);
            for (int i = 0; i < iterations; i++) {
                cgaOptimizer1.runIteration();
            }
            System.out.println("Maximum fulfillment of the clauses after the genetic algorithm: ");
            System.out.println(cgaOptimizer1.findBestResult() + "/" + cgaOptimizer1.getcMax3SatProblem().getClauses().size() / 3);

            System.out.println("If you want to continue choose 1, to finish choose any number:");
            int choice = scanner.nextInt();
            if (choice != 1) {
                return;
            }
        }

    }
}
