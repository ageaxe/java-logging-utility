java-logging-utility
=====================

Objective:
----------
The objective of this code is to log debug messages whenever an exception occurs. The debug messages are maintained in memory using a FIFO queue, and when an exception happens, the debug messages for the current thread are dumped.

Implementation Details:
-----------------------
1. **DebugLoggerAdapter**:
   - The `DebugLoggerAdapter` class implements the `Logger` interface.
   - It maintains a queue of debug messages in memory.
   - The queue operates in a FIFO (First In, First Out) manner.
   - When the queue reaches its maximum capacity, the oldest message is removed to make space for new messages.
   - The size of the queue can be controlled using the `maxDebugMessages` environment variable.

2. **Debug Logging**:
   - The `debug(String msg)` method is used to add debug messages to the queue.
   - If the queue is full, the oldest message is removed before adding the new message.

3. **Exception Handling**:
   - When an exception occurs, the debug messages for the current thread are dumped.
   - This helps in diagnosing the issue by providing the sequence of debug messages leading up to the exception.

Usage:
------
- To log a debug message, use the `debug(String msg)` method of the `DebugLoggerAdapter` class.
- Ensure that the `DebugLoggerAdapter` is properly configured to handle exceptions and dump the debug messages.
- Set the `maxDebugMessages` environment variable to control the size of the debug message queue.

References:
-----------
- For more information, visit the SLF4J FAQ: https://www.slf4j.org/faq.html#slf4j_compatible