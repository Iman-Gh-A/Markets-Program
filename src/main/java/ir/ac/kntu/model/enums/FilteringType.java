package ir.ac.kntu.model.enums;

public enum FilteringType {
    DESCENDINGRATE(1),
    ASCENDINGRATE(2),
    DESCENDINGCOMMENTNUMBER(3),
    ASCENDINGCOMMENTNUMBER(4);

    private final int num;

    FilteringType(int num) {
        this.num = num;
    }

    public FilteringType selectByNum(int num) {
        for (FilteringType current : FilteringType.values()) {
            if (current.num == num) {
                return current;
            }
        }
        throw new IllegalArgumentException("\tError: The number of option should be between 1-4");
    }
}
