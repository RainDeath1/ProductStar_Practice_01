import java.util.Objects;
import java.util.stream.Stream;

/*
Прндставляет собой ввод, выбранный пользователем
 */
public enum Action {

    EXIT(0,false),

    CREATE(1, true),
    UPDATE(2, true),
    DELETE(3, true),
    STAT_BY_COURSE(4, false),
    STAT_BY_CITY(5, false),
    SEARCH(6, true),
    ERROR(-1, false);
    private int code;
    private boolean requireAdditionalData;

    Action(int code, boolean requireAdditionalData){
        this.code = code;
        this.requireAdditionalData = requireAdditionalData;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public boolean isRequireAdditionalData() {
        return requireAdditionalData;
    }

    public void setRequireAdditionalData(boolean requireAdditionalData) {
        this.requireAdditionalData = requireAdditionalData;
    }

    public static Action fromCode(int code){
        return Stream.of(Action.values())
                .filter(action -> Objects.equals(action.getCode(), code))
                .findFirst().orElseGet(() -> {
                    System.out.println("Неизвестный код действия" + code);
                return Action.ERROR;});
    }
}
