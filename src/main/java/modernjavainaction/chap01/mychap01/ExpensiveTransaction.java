package modernjavainaction.chap01.mychap01;

import modernjavainaction.chap06.GroupingTransactions;

import java.util.*;
import java.util.stream.Collectors;

import static modernjavainaction.chap06.GroupingTransactions.*;

public class ExpensiveTransaction {

    enum Color{
        RED,GREEN
    }

    public static void main(String[] args) {

        //getExpensiveTransaction();
        //getExpensiveTransactionStream();

    }

    private static List<Apple>filterGreenApples(List<Apple>inventory){
        return  inventory.stream()
                .filter(apple -> Color.GREEN.equals(apple.getColor()))
                .collect(Collectors.toList());
    }

    private static void getExpensiveTransaction(){
        List<Transaction> transactions = GroupingTransactions.transactions;

        Map<GroupingTransactions.Currency,List<Transaction>>transactionByCurrency=new HashMap<>();

        for (Transaction transaction : transactions){
            //System.out.println("Curr: " + transaction.getCurrency() + " summa: " + transaction.getValue());
            GroupingTransactions.Currency currency=transaction.getCurrency();
            if(transactionByCurrency.containsKey(currency)){
                List<Transaction> transactionList = transactionByCurrency.get(currency);
                transactionList.add(transaction);
            }else{
                List<Transaction>transactionList=new ArrayList<>();
                transactionList.add(transaction);
                transactionByCurrency.put(currency,transactionList);
            }
        }

        System.out.println(transactionByCurrency);
    }

    private static void getExpensiveTransactionStream(){
        List<Transaction> transactions = GroupingTransactions.transactions;

        Map<GroupingTransactions.Currency, List<Transaction>> collect = transactions.stream()
                .filter(transaction -> transaction.getValue() > 1000)
                .collect(Collectors.groupingBy(Transaction::getCurrency));

        System.out.println(collect);


    }

}
