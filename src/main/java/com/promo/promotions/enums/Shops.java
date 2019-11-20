package com.promo.promotions.enums;

public enum Shops {

    Allegro(new Categories[]{Categories.All, Categories.Electronic, Categories.Cars, Categories.Cloths, Categories.Entertainment},
            "https://allegro.pl", false),
    Amazon(new Categories[]{Categories.All, Categories.Electronic, Categories.Entertainment},
            "https://www.amazon.com", true),
    MediaExpert(new Categories[]{Categories.All, Categories.Electronic}, "https://mediaexpert.pl", true),
    MediaMarkt(new Categories[]{Categories.All, Categories.Cloths}, "", true),
    Zalando(new Categories[]{Categories.All, Categories.Cloths}, "", false),
    AboutYou(new Categories[]{Categories.All, Categories.Cloths}, "", false),
    AnsWear(new Categories[]{Categories.All, Categories.Cloths}, "", false),
    Otomoto(new Categories[]{Categories.All, Categories.Cloths}, "", false),
    AutoScout24(new Categories[]{Categories.All, Categories.Cloths}, "", false),
    Sprzedajemy(new Categories[]{Categories.All, Categories.Cloths}, "", false);

    private String originUrl;
    private boolean needsOriginUrl;
    private Categories[] categories;

    Shops(Categories[] categories, String originUrl, boolean needsOriginUrl) {
        this.categories = categories;
        this.originUrl = originUrl;
        this.needsOriginUrl = needsOriginUrl;
    }
}
