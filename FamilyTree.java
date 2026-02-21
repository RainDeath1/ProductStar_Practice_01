package Practice_03;
import java.util.*;

public class FamilyTree {
    private String name;
    private List<FamilyTree> parents = new ArrayList<>();
    private List<FamilyTree> children = new ArrayList<>();

    private static Map<String, FamilyTree> registry = new HashMap<>();

    public FamilyTree(String name) {
        this.name = name;
        registry.put(name, this);
    }

    private static FamilyTree getOrCreate(String name) {
        FamilyTree person = registry.get(name);
        if(person == null){
            person = new FamilyTree(name);
            registry.put(name, person);
        }
        return person;
    }

    public void addChild(String name) {
        FamilyTree child = getOrCreate(name);

        if (!children.contains(child)) {
            children.add(child);
            child.parents.add(this);
        }
    }

    public void addParent(String name) {
        FamilyTree parent = getOrCreate(name);

        if (!parents.contains(parent)) {
            children.add(parent);
            parent.children.add(this);
        }
    }
    public static Set<String> findRelatives(String name){
        FamilyTree start = registry.get(name);
        Set<String> visited = new HashSet<>();

        if(start == null) return visited;

        dfs(start,visited);
        visited.remove(name);
        return visited;
    }

    private static void dfs(FamilyTree person, Set<String> visited){
        if(!visited.add(person.name)) return;

        for (FamilyTree p : person.parents)
            dfs(p,  visited);

        for(FamilyTree c : person.children)
            dfs(c, visited);
    }

    public void printTree(){
        printTree("", new HashSet<>());
    }

    private void printTree(String indent,Set<FamilyTree> visited){
        if(!visited.add(this)) return;
        System.out.println(indent + name);

        for(FamilyTree child:children){
            child.printTree(indent +  " ", visited);
        }
    }

    public static void main(String[] args) {
        FamilyTree grandpa = new FamilyTree("Иван");
        grandpa.addChild("Алексей");
        FamilyTree grandma = new FamilyTree("Лия");
        registry.get("Алексей").addChild("Дмитрий");
        registry.get("Алексей").addChild("Мария");

        registry.get("Алексей").addParent("Иван");
        registry.get("Алексей").addParent("Лия");
        grandma.printTree();
        grandpa.printTree();

        System.out.println("\nРодственники Дмитрия");
        System.out.println(FamilyTree.findRelatives("Мария"));
    }
}
