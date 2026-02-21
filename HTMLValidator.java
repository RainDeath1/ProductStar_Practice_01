package Practice_03;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLValidator {

    public static boolean isValidHTML(String html){
        Stack<String> stack = new Stack<>();

        Pattern pattern = Pattern.compile("<(/?\\w+>)");
        Matcher matcher = pattern.matcher(html);

        while(matcher.find()){
            String tag = matcher.group(1);

            if(tag.startsWith("/")){
                String closingTag = tag.substring(1);

                if(stack.isEmpty()|| !stack.peek().equals(closingTag)){
                    return false;
                }
                stack.pop();
            } else{
                stack.push(tag);
            }
        }
        return  stack.isEmpty();
    }

    public static void main(String[] args){
        String[] tests = {
                "<div><p>Какой-то текст</p></>",
                "<h1>Заголовок</h1>",
                "<div><span>Текст</span><p></p></div>",
                "<div><p>текст</div></p>",
                "<div>Текст</p>",
                "<p><div>Тест</p></div>"
        };

        for(String test : tests){
            System.out.println(test + "->" + isValidHTML(test));
        }
    }
}
