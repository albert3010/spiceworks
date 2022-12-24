package practice_lld_2023.lld_2022.splitwise.Constants;

public class Constants {

    private static Integer userId = 1;
    private static Integer groupId = 1;
    private static Integer expenseId = 1;

    public static Integer getUserId() {
        return userId++;
    }

    public static Integer getGroupId() {
        return groupId++;
    }

    public static Integer getExpenseId() {
        return expenseId++;
    }
}
