package practice_system_design.machine_coding.splitwise_system.services;

import practice_system_design.machine_coding.splitwise_system.Bill;
import practice_system_design.machine_coding.splitwise_system.GiveAndTake;
import practice_system_design.machine_coding.splitwise_system.PersonAToB;
import practice_system_design.machine_coding.splitwise_system.UserToBill;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SettleUpService {

    public static List<GiveAndTake> getIndividualBill(Bill bill) {
        List<UserToBill> userToBills = bill.getUserToBills();
        List<GiveAndTake> giveAndTakes = new ArrayList<>();

        userToBills.forEach(userToBill -> {
            double amountToSettle = userToBill.getAmountPaid() - userToBill.getAmountSpent();
            giveAndTakes.add(new GiveAndTake(userToBill.getUserId(), amountToSettle));
        });
        return giveAndTakes;
    }

    public static List<PersonAToB> getPersonsAmount(List<GiveAndTake> giveAndTakes) {
        PriorityQueue<GiveAndTake> takes = new PriorityQueue<>((x, y) -> (int) y.getAmount() - (int) x.getAmount());
        PriorityQueue<GiveAndTake> gives = new PriorityQueue<>((x, y) -> (int) x.getAmount() - (int) y.getAmount());

        for (GiveAndTake giveAndTake : giveAndTakes) {
            if (giveAndTake.getAmount() > 0) {
                takes.add(giveAndTake);
            }
            if (giveAndTake.getAmount() < 0) {
                gives.add(giveAndTake);
            }
        }
        return calculatePersonAtoB(takes, gives);

    }

    private static List<PersonAToB> calculatePersonAtoB(PriorityQueue<GiveAndTake> takes, PriorityQueue<GiveAndTake> gives) {
        List<PersonAToB> personAToBList = new ArrayList<>();
        while (!takes.isEmpty()) {
            GiveAndTake take = takes.poll();
            GiveAndTake give = gives.poll();

            double sumAmount = take.getAmount() + give.getAmount();
            double amount = take.getAmount();

            if (sumAmount > 0) {
                takes.add(new GiveAndTake(take.getUserId(), sumAmount));
                amount = give.getAmount();
            }
            if (sumAmount < 0) {
                gives.add(new GiveAndTake(give.getUserId(), sumAmount));
            }
            personAToBList.add(new PersonAToB(give.getUserId(), take.getUserId(), Math.abs(amount)));
        }
        return personAToBList;
    }

}
