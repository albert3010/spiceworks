package practice_system_design.machine_coding.splitwise_system;

public class PersonAToB {
    int personAId;
    int personBId;
    double amount;

    public PersonAToB(int personAId, int personBId, double amount) {
        this.personAId = personAId;
        this.personBId = personBId;
        this.amount = amount;
    }

    public int getPersonAId() {
        return personAId;
    }

    public int getPersonBId() {
        return personBId;
    }

    public double getAmount() {
        return amount;
    }
}
