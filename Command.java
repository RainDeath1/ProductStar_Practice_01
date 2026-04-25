public class Command {
    private Action action;
    private String data;

    public Command(Action action){
        this.action = action;
    }
    public Command(String data,Action action) {
        this.data = data;
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
