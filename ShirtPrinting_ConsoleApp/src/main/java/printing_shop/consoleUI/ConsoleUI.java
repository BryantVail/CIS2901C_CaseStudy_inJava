package printing_shop.consoleUI;

public class ConsoleUI {

    public static String UnderscoreLine(int length){
        String line = "";
        for(int i = 0; i < length; i++){
            line += "_";
        }
        return line;
    }
}
