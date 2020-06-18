package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 270, 200};
    public static int[] heroesDamage = {20, 15, 25};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic"};
    public final static int MEDIC_HEALS = 20;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();

        }


    }

    public static void changeDefence() {
        Random random = new Random();
        int randomNumber = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomNumber];
        if (bossDefenceType.equals(heroesAttackType[3])) {
            changeDefence();
        } else {
            System.out.println("Boss got" + " " + bossDefenceType);
        }

    }

    public static void round() {
        changeDefence();
        heroesHit();
        medicHeals();
        if (bossHealth > 0) {
            bossHits();
        }
        printStatistics();
    }


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;

        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("___________________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magician health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("___________________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }

            }

        }

    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coef = random.nextInt(5) + 2;
                    System.out.println("Critical damage " + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }

                }
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void medicHeals() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + MEDIC_HEALS;
                return;
            }


        }

    }
}








