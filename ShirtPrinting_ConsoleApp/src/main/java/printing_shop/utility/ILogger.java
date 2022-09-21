package printing_shop.utility;


public interface ILogger{
    void Log(
            String entityId,
            String entityType,
            String changeType,
            String newValue);
}