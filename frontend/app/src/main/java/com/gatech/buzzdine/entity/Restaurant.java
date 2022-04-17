package com.gatech.buzzdine.entity;

public enum Restaurant {
    Cypress_Street_Pint_Plate("Cypress Street Pint & Plate", 0), Tindrum("Tindrum", 1),
    Atwoods("Atwoods", 2), Moe_s("Moes", 3), Gyro_bros("Gyro bros", 4),
    Waffle_house("Waffle house", 5), Umma_s("Ummas", 6),
    Starbucks("Starbucks", 7), Rising_Roll("Rising Roll", 8), Herban_Fix("Herban Fix", 9),
    Poor_Calvin_s("Poor Calvins", 10), Ecco_Midtown("Ecco Midtown", 11),
    Twisted_Taco("Twisted Taco", 12), The_Varsity("The Varsity", 13), Rowdy_Tiger_Whiskey_Bar_Kitchen("Rowdy Tiger Whiskey Bar &amp; Kitchen", 14),
    Dos_Amigos("Dos Amigos", 15), Sublime_Doughnut("Sublime Doughnut", 16),
    Lucky_Buddha("Lucky Buddha", 17), Anticos("Anticos", 18), Cafe_intermezzo("Cafe intermezzo", 19),
    Nova_Sushi("Nova Sushi", 20), Kaldi_Coffee("Kaldis Coffee", 21), Pho_King("Pho King", 22),
    La_Fonda("La Fonda", 23), Chick_fil_a("Chick-fil-a", 24), McDonalds("McDonalds", 25),
    Cook_Out("Cook Out", 26), Krystal("Krystal", 27);

    String name;
    int index;
    Restaurant(String name, int index){
        this.name = name;
        this.index = index;
    }

    public static Restaurant getRestaurant(String word){
        for(Restaurant restaurant: Restaurant.values()){
            if (restaurant.getName().equals(word)){
                return restaurant;
            }
        }
        return Cypress_Street_Pint_Plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
