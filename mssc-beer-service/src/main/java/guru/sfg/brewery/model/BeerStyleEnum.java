package guru.sfg.brewery.model;

/**
 * Created by jt on 2019-05-12.
 */
public enum BeerStyleEnum {
    LAGER, PILSNER, STOUT, GOSE, PORTER, ALE, WHEAT, IPA, PALE_ALE, SAISON;

    private String styleName;

    BeerStyleEnum() {
        this.styleName = this.name();
    }

    BeerStyleEnum(String styleName) {
        this.styleName = styleName.toUpperCase();
    }

    public String getStyleName() {
        return styleName;
    }

    public static BeerStyleEnum fromString(String styleName) {
        for (BeerStyleEnum style : BeerStyleEnum.values()) {
            if (style.styleName.equalsIgnoreCase(styleName)) {
                return style;
            }
        }
        throw new IllegalArgumentException("Invalid beer style: " + styleName);
    }
}
