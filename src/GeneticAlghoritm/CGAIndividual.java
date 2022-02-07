package GeneticAlghoritm;

import java.util.ArrayList;
import java.util.HashMap;

public class CGAIndividual {
    private final HashMap<Integer, Integer> genotype;
    private final double mutationChance;
    private final double crossoverChance;

    CGAIndividual(HashMap<Integer, Integer> genotype, double mutationChance, double crossoverChance) {
        this.genotype = genotype;
        this.crossoverChance = crossoverChance;
        this.mutationChance = mutationChance;
    }

    void mutation() {
        for (HashMap.Entry<Integer, Integer> entry : genotype.entrySet()) {
            if (Math.random() < mutationChance) {
                genotype.replace(entry.getKey(), entry.getValue() * -1);
            }
        }
    }

    ArrayList<CGAIndividual> crossover(CGAIndividual anotherParent) {
        ArrayList<CGAIndividual> kids = new ArrayList<>();
        HashMap<Integer, Integer> genotypeKid1 = new HashMap<>();
        HashMap<Integer, Integer> genotypeKid2 = new HashMap<>();

        if (Math.random() < crossoverChance) {
            for (HashMap.Entry<Integer, Integer> entry : genotype.entrySet()) {
                if (Math.random() < 0.5) {
                    genotypeKid1.put(entry.getKey(), entry.getValue());
                    genotypeKid2.put(entry.getKey(), anotherParent.genotype.get(entry.getKey()));
                } else {
                    genotypeKid1.put(entry.getKey(), anotherParent.genotype.get(entry.getKey()));
                    genotypeKid2.put(entry.getKey(), entry.getValue());

                }
            }
        } else {
            for (HashMap.Entry<Integer, Integer> entry : genotype.entrySet()) {
                genotypeKid1.put(entry.getKey(), entry.getValue());
                genotypeKid2.put(entry.getKey(), anotherParent.genotype.get(entry.getKey()));
            }
        }
        CGAIndividual kid1 = new CGAIndividual(genotypeKid1, mutationChance, crossoverChance);
        CGAIndividual kid2 = new CGAIndividual(genotypeKid2, mutationChance, crossoverChance);
        kids.add(kid1);
        kids.add(kid2);
        return kids;
    }


    int fitness(CMax3SatProblem problem) {
        return problem.Compute(genotype);
    }

}
