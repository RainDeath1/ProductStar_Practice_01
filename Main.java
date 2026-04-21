package org.example;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        RankingSystem rankingSystem = new RankingSystem();

        Player first = new Player(1, "Alice in Chains", 1500);
        Player second = new Player(2, "SkidRow", 1600);
        Player third = new Player(3, "Tools", 1950);

        rankingSystem.addPlayer(first);
        rankingSystem.addPlayer(second);
        rankingSystem.addPlayer(third);
        try{
            System.out.println("\nТоп 3 игрока:");
            List<Player> topPlayer = rankingSystem.getTopPlayers(3);
            for (int i = 0; i < topPlayer.size(); i++) {
                Player player = topPlayer.get(i);
                System.out.println((i+1) + ". " + player.getName() + " - " + player.getRating());
            }
             rankingSystem.updatePlayerRating(2, 1800);
            System.out.println("\nТоп 3 игрокапосле обновления:");
            topPlayer = rankingSystem.getTopPlayers(3);

            for (int i = 0; i < topPlayer.size(); i++) {
                Player player = topPlayer.get(i);
                System.out.println((i+1) + ". " + player.getName() + " - " + player.getRating());
            }

            System.out.println("Текущий ранг Alice in Chains: " + rankingSystem.getPlayerRank(1));
            System.out.println("Попытка получить рейтинг несуществующего игрока: ");
            System.out.println(rankingSystem.getPlayerRank(4));


        }catch (PlayerNotFoundException| InvalidTopRequestException e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}