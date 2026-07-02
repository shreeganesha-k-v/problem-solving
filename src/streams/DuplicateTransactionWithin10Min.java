package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateTransactionWithin10Min {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(

                new Transaction("T1", "U1", 500, 10),
                new Transaction("T2", "U2", 100, 20),
                new Transaction("T3", "U1", 500, 15),
                new Transaction("T4", "U1", 700, 40),
                new Transaction("T5", "U2", 100, 35)
        );

        // Group transactions by userId and amount, then check for duplicates within 10 minutes
        Map<String, List<Transaction>> possibleDuplicates = transactions.stream()
                .collect(Collectors.groupingBy(t->t.userId() + "-" + t.amount()));


        possibleDuplicates.values()
                .stream()
                .filter(v->v.size()>1)
                .flatMap(t->{
                    List<String> suspicious = new ArrayList<>();

                    for(int i=0;i<t.size();i++){
                        for(int j=i+1;j<t.size();j++){
                            if(Math.abs(t.get(i).minutes()-t.get(j).minutes())<=10){
                                suspicious.add(t.get(i).id());
                                suspicious.add(t.get(j).id());
                            }
                        }
                    }
                    return suspicious.stream();
                })
                .distinct()
                .forEach(System.out::println);
    }

    record Transaction(String id , String userId , double amount , long minutes){};
}
