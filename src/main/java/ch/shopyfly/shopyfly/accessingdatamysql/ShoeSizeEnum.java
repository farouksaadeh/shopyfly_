package ch.shopyfly.shopyfly.accessingdatamysql;

public enum ShoeSizeEnum {
    SIZE_39("39"), SIZE_40("40"), SIZE_40_5("40.5"), SIZE_41("41"), SIZE_42("42"), SIZE_43("43"), SIZE_44("44"), SIZE_45("45"), SIZE_46("46"), SIZE_47("47"), SIZE_48("48"), SIZE_49("49");


    private String size;

    ShoeSizeEnum(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }


    public static ShoeSizeEnum fromSize(String size) {
        for (ShoeSizeEnum b : ShoeSizeEnum.values()) {
            if (b.getSize().equals(size)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + size + "'");
    }
}

