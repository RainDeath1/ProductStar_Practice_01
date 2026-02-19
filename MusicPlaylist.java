package Practice_03;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;


public class MusicPlaylist {
    private ArrayList<String> songs;
    private int currentIndex = -1;

    public MusicPlaylist(){
        this.songs = new ArrayList<>();
    }

    public void addSongs(String title){
        if(title ==null || title.isBlank()){
            System.out.println("Название песни пустое");
            return;
        }
        songs.add(title);
        System.out.println("Добавлена песня: " + title);
    }

    public void removeSongs(int index) {
        if (isValidIndex(index)) {
            String removed = songs.remove(index);
            System.out.println("Удалена песня: " + removed);
            if(index == currentIndex) currentIndex=-1;
            else if (index < currentIndex) currentIndex--;
        } else {
            System.out.println("Ошибка: Неверный индекс для удаления.");
        }
    }

    public void moveSongs(int from, int to) {
        if (from == to) {
            System.out.println("Позиция совпадает, перемещение не требуется");
            return;
        }
        if (from < 0 || from >= songs.size()) {
            System.out.println("Ошибка: from вне диапазона");
            return;
        }else if
        ( to < 0 || to>songs.size()){
            System.out.println("Ошибка: to вне диапазона");
            return;
        }
        if(from < to) {
            to--;
        }

        String song = songs.remove(from);
        songs.add(to, song);
        System.out.println("Песня: ' " + song +"' перемещена с позиции " + from + " на " + to);
    }

    public void shuffle(){
        if(songs.size() < 2){
            return;
        }

        Random rand = new Random();

        for (int i = songs.size() - 1 ; i > 0 ; i--) {
            int j = rand.nextInt(i+1);
            String temp = songs.get(i);
            songs.set(i, songs.get(j));
            songs.set(j, temp);
        }
        System.out.println("Плейлист перемещен");
    }

    public void display(){
        System.out.println("---Текущий плейлист(всего: " +songs.size() + ") ---");
        for (int i = 0; i < songs.size(); i++) {
            String mark = (i==currentIndex) ? "▶": "";
            System.out.println(i + ". " +songs.get(i) + mark);
        }
        System.out.println("-----------------------------------------");
    }
    public void search(String name){
        boolean found = false;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).toLowerCase().contains(name.toLowerCase())){
                System.out.println("Найдено: " + songs.get(i) + "(индекс " + i + ")");
                found = true;
            }
        }
        if(!found) System.out.println("Песня не найдена");
    }

    public void play(int index){
        if (!isValidIndex(index)) {
            System.out.println("Нет такой песни");
            return;
        }
        currentIndex = index;
        System.out.println("Сейчас играет:" + songs.get(currentIndex));
    }

    public void next(){
        if(songs.isEmpty()) return;

        if(currentIndex == -1) currentIndex=0;
        else currentIndex = (currentIndex+1) % songs.size();

        System.out.println("Следующая: " + songs.get(currentIndex));
    }

    private boolean isValidIndex(int index){
        return index >= 0 && index < songs.size();
    }

    // ===== МЕНЮ =====
    public static void main(String[] args) {
        MusicPlaylist myPlaylist = new MusicPlaylist();

        myPlaylist.addSongs("Bohemian_Rhapsody");
        myPlaylist.addSongs("Bards_Song");
        myPlaylist.addSongs("За_горизонтом_звезд");

        myPlaylist.display();

        myPlaylist.moveSongs(0,2);
        myPlaylist.display();

        myPlaylist.shuffle();
        myPlaylist.display();

        myPlaylist.play(1);
        myPlaylist.display();

        myPlaylist.removeSongs(2);
        myPlaylist.display();

        try(Scanner sc = new Scanner(System.in)){

            while(true){
                System.out.println("""
                1 Добавить песню
                2 Удалить песню
                3 Переместить песню
                4 Перемешать
                5 Показать плейлист
                6 Поиск
                7 Играть по индексу
                8 Следующая
                0 Выход
                """);
                int cmd = sc.nextInt();
                sc.nextLine();
                switch (cmd) {
                    case 1 -> {
                        System.out.println("Название: ");
                        myPlaylist.addSongs(sc.nextLine());
                    }

                    case 2 -> {
                        System.out.println("Индекс: ");
                        myPlaylist.removeSongs(sc.nextInt());
                    }

                    case 3 -> {
                        System.out.println("Откуда: ");
                        int f = sc.nextInt();
                        System.out.println("Куда: ");
                        int t = sc.nextInt();
                        myPlaylist.moveSongs(f, t);
                    }
                    case 4 -> myPlaylist.shuffle();
                    case 5 -> myPlaylist.display();
                    case 6 -> {
                        System.out.println("Поис:");
                        myPlaylist.search(sc.nextLine());
                    }
                    case 7 -> {
                        System.out.println("Индекс: ");
                        myPlaylist.play(sc.nextInt());
                    }
                    case 8 -> myPlaylist.next();
                    case 0 -> {
                        System.out.println("Выход...");
                        return;
                    }
                    default -> System.out.println("Неизвестная командв");
                }
            }
        }
    }
}
