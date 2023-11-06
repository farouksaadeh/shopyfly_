package ch.shopyfly.shopyfly.accessingdatamysql;

public enum ShoeSizeEnum {
    SIZE_39("39"), SIZE_40_5("40.5"), SIZE_41("41");

    private String size;

    ShoeSizeEnum(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
