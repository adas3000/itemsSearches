package com.promo.promotions.enums;

public enum Category {
    Electronic(new SerachIn[]{SerachIn.Amazon, SerachIn.Allegro, SerachIn.MediaExpert}),
    Cloths(new SerachIn[]{SerachIn.Allegro}),
    Cars(new SerachIn[]{SerachIn.Allegro}),
    Books(new SerachIn[]{SerachIn.Allegro});

    public enum SerachIn {
        Allegro,Amazon,MediaExpert,MediaMarkt,Zalando,AboutYou,AnsWear,Otomoto,AutoScout24,Sprzedajemy;
    }

    private SerachIn[] serachIn;

    Category(SerachIn[] serachIns){
        this.serachIn = serachIns;
    }

    public SerachIn[] getSerachIn() {
        return serachIn;
    }
}
