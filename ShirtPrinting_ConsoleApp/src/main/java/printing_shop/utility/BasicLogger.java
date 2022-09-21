package printing_shop.utility;

public class BasicLogger implements ILogger{

    String connectionString;

    public BasicLogger(String connectionString){
        this.connectionString = connectionString;
    }
    @Override
    public void Log(String entityId, String entityType, String changeType, String newValue) {

    }
}
