package org.example;

import java.util.*;

public class RankingSystem {
    private TreeMap<Integer, Set<Player>> playerRankings;

    public RankingSystem(){
        playerRankings = new TreeMap<>(Collections.reverseOrder());
    }

    public void addPlayer(Player player){
        playerRankings.putIfAbsent(player.getRating(), new HashSet<>());
        playerRankings.get(player.getRating()).add(player);

        System.out.println("Добавлен игрок: " + player);
    }

    public void updatePlayerRating(int playerId, int newRating)
            throws PlayerNotFoundException{
        Player targetPlayer = null;
        int oldRating = 0;

        for (Map.Entry<Integer, Set<Player>> entry: playerRankings.entrySet()){
            for (Player player:entry.getValue()){
                if(player.getId() == playerId){
                    targetPlayer = player;
                    oldRating = entry.getKey();
                    break;
                }
            }
        }
        if(targetPlayer == null){
            throw new PlayerNotFoundException(
                    "Игрок с ID" + playerId + " не найден в системе"
            );
        }

        playerRankings.get(oldRating).remove(targetPlayer);
        if(playerRankings.get(oldRating).isEmpty()){
            playerRankings.remove(oldRating);
        }

        targetPlayer.setRating(newRating);

        playerRankings.putIfAbsent(newRating,new HashSet<>());
        playerRankings.get(newRating).add(targetPlayer);
        System.out.println("Обновлен рейтинг игрока"+ targetPlayer.getName() + ": " + newRating);

    }

    public List<Player> getTopPlayers(int n)throws InvalidTopRequestException{
        List<Player> topPlayers = new ArrayList<>();

        int totalPlayers = 0;
        for (Set<Player> players: playerRankings.values()){
            totalPlayers += players.size();
        }

        if (n>totalPlayers){
            throw new InvalidTopRequestException("Запрошено большое количество" +
                    " игроков. Всего игроков:"+ topPlayers);
        }

        for (Set<Player> players : playerRankings.values()){
            for (Player player : players){
                topPlayers.add(player);

                if(topPlayers.size() == n){
                    return topPlayers;
                }
            }
        }
        return topPlayers;
    }
    public int getPlayerRank(int playerId) throws PlayerNotFoundException{
        int rank = 1;
        for(Set<Player> players : playerRankings.values()){
            for (Player player : players){
                if(player.getId() == playerId){
                    return  rank;
                }
                rank++;
            }
        }
        throw new PlayerNotFoundException("Игрок с ID" + playerId +
                " не найден в системе");
    }

}
