package RoleBasedAccessControl;

public enum Action {
    READ(1),
    WRITE(2),
    DELETE(3);

    int rightId;

    Action(int rightId) {
        this.rightId = rightId;
    }
}
