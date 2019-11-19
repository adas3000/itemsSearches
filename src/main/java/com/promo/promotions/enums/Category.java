package com.promo.promotions.enums;

public enum Category {
    Electronic(new SerachIn[]{SerachIn.Amazon, SerachIn.Allegro, SerachIn.MediaExpert}),
    Cloths(new SerachIn[]{SerachIn.Allegro}),
    Cars(new SerachIn[]{SerachIn.Allegro}),
    Books(new SerachIn[]{SerachIn.Allegro});

    public enum SerachIn {
        Allegro("https://allegro.pl",false), Amazon("https://www.amazon.com",true),
        MediaExpert("https://mediaexpert.pl",true), MediaMarkt("",true),
        Zalando("",false), AboutYou("",false),
        AnsWear("",false), Otomoto("",false),
        AutoScout24("",false), Sprzedajemy("",false);

        private String originUrl;
        private boolean needsOriginUrl;

        SerachIn(String originUrl, boolean needsOriginUrl) {
            this.originUrl = originUrl;
            this.needsOriginUrl = needsOriginUrl;
        }

        public String getOriginUrl() {
            return originUrl;
        }

        public void setOriginUrl(String originUrl) {
            this.originUrl = originUrl;
        }

        public boolean isNeedsOriginUrl() {
            return needsOriginUrl;
        }

        public void setNeedsOriginUrl(boolean needsOriginUrl) {
            this.needsOriginUrl = needsOriginUrl;
        }
    }


    private SerachIn[] serachIn;

    Category(SerachIn[] serachIns) {
        this.serachIn = serachIns;
    }

    public SerachIn[] getSerachIn() {
        return serachIn;
    }
}
