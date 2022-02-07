package GeneticAlghoritm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CMax3SatProblem {
    private ArrayList<Integer> clauses;
    private ArrayList<Integer> values;


    void Load(String fileName) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.replaceAll("[()]", "");
                line = line.trim();
                String[] parts = line.split(" {2}");
                arrayList.add(Integer.parseInt(parts[0]));
                arrayList.add(Integer.parseInt(parts[1]));
                arrayList.add(Integer.parseInt(parts[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HashSet<Integer> hashSet = new HashSet<>(arrayList);
        ArrayList<Integer> withoutRepetitions = new ArrayList<>(hashSet);
        clauses = arrayList;
        values = withoutRepetitions;
    }

    int Compute(HashMap<Integer, Integer> solution) {
        int trueClauses = 0;
        int counter = 0;
        boolean accept = false;
        for (int i = 0; i < clauses.size(); i++) {
            if (clauses.get(i) * solution.get(Math.abs(clauses.get(i))) > 0) {
                accept = true;
            }
            if (counter == 2) {
                if (accept) {
                    trueClauses++;
                }
                accept = false;
                counter = -1;
            }
            counter++;
        }
        return trueClauses;
    }

    public ArrayList<Integer> getClauses() {
        return clauses;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }
}
