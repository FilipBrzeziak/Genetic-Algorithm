package GeneticAlghoritm;

import java.util.ArrayList;
import java.util.HashMap;

public class CGAOptymalizer {
    private final double mutationChance;
    private final double crossoverChance;
    private ArrayList<CGAIndividual> population;
    private final CMax3SatProblem cMax3SatProblem;

    CGAOptymalizer(double mutationChance, double crossoverChance) {
        this.mutationChance = mutationChance;
        this.crossoverChance = crossoverChance;
        this.population = new ArrayList<>();
        this.cMax3SatProblem = new CMax3SatProblem();
    }

    void loadInstance(String nameOfFile) {
        cMax3SatProblem.Load(nameOfFile);
    }

    int findBestResult() {
        int max = 0;
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).fitness(cMax3SatProblem) > max) {
                max = population.get(i).fitness(cMax3SatProblem);
            }
        }

        return max;
    }

    void initialize(int sizeOfPopulation) {
        for (int i = 0; i < sizeOfPopulation; i++) {
            CGAIndividual newIndividual = new CGAIndividual(prepareSolution(), mutationChance, crossoverChance);
            population.add(newIndividual);
        }
    }

    void runIteration() {
        ArrayList<CGAIndividual> newPopulation = new ArrayList<>();

        while (newPopulation.size() < population.size()) {

            CGAIndividual parent1 = chooseParent();
            CGAIndividual parent2 = chooseParent();

            ArrayList<CGAIndividual> kids = parent1.crossover(parent2);
            CGAIndividual kid1 = kids.get(0);
            CGAIndividual kid2 = kids.get(1);
            kid1.mutation();
            kid2.mutation();
            newPopulation.add(kid1);
            newPopulation.add(kid2);
        }
        population = newPopulation;
    }

    HashMap<Integer, Integer> prepareSolution() {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < cMax3SatProblem.getValues().size(); i++) {
            if (Math.random() > 0.5) {
                result.put(cMax3SatProblem.getValues().get(i), cMax3SatProblem.getValues().get(i));
            } else {
                result.put(cMax3SatProblem.getValues().get(i), cMax3SatProblem.getValues().get(i) * -1);
            }
        }
        return result;
    }

    CGAIndividual chooseParent() {
        int parentIndex1 = (int) (Math.random() * population.size());
        int parentIndex2 = (int) (Math.random() * population.size());

        if (population.get(parentIndex1).fitness(cMax3SatProblem) > population.get(parentIndex2).fitness(cMax3SatProblem)) {
            return population.get(parentIndex1);
        } else {
            return population.get(parentIndex2);
        }
    }

    public CMax3SatProblem getcMax3SatProblem() {
        return cMax3SatProblem;
    }

}
