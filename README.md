# Java Logger Utility with SLF4J Integration

This utility captures debug logs in a FIFO queue and dumps them when an exception occurs. It helps in diagnosing issues by providing the sequence of debug messages leading up to the exception.

## Features
1. **FIFO Queue for Debug Messages**:
   - Operates in a FIFO (First In, First Out) manner.
   - When the queue reaches its maximum capacity, the oldest message is removed to make space for new messages.
   - The size of the queue can be controlled using the `maxDebugMessages` environment variable.

2. **Debug Logging**:
   - The `debug(String msg)` method is used to add debug messages to the queue.
   - If the queue is full, the oldest message is removed before adding the new message.

3. **Exception Handling**:
   - When an exception occurs, the debug messages are dumped.
   - This helps in diagnosing the issue by providing the sequence of debug messages leading up to the exception.

## Installation
To include this utility in your project, add the following dependency to your `pom.xml` if you are using Maven:
```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>debug-logger</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Usage
To log a debug message, use the debug(String msg) method of the DebugLoggerAdapter class. Ensure that the DebugLoggerAdapter is properly configured to handle exceptions and dump the debug messages.

## Example
```java
import com.example.DebugLoggerAdapter;

public class Example {
    private static final DebugLoggerAdapter logger = new DebugLoggerAdapter();

    public static void main(String[] args) {
        try {
            logger.debug("Starting application...");
            // Your application logic here
            logger.debug("Application running...");
        } catch (Exception e) {
            logger.dumpDebugMessages(e);
        }
    }
}
```

## Configuration
Set the `maxDebugMessages` environment variable to control the size of the debug message queue. For example, in a Unix-based system, you can set it as follows:
```sh
export maxDebugMessages=100
```

## Future Enhancements
Currently, this utility captures logs at the process level. Future enhancements may include capturing logs at the thread level. However, managing thread-level debug logs in applications with many concurrent threads could be challenging due to the complexity of garbage collecting thread-specific logs. Even with the latest Java implementations, such as advanced garbage collectors (G1, ZGC, Shenandoah) and `ThreadLocal` for thread-specific data, careful management is required to avoid memory leaks and performance issues.

## References
For more information, visit the [SLF4J FAQ](https://www.slf4j.org/faq.html#slf4j_compatible).
