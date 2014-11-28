package org.netpod.axon;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.axonframework.commandhandling.gateway.Timeout;
import org.axonframework.common.annotation.MetaData;
import org.netpod.axon.command.MyPayloadType;

public interface MyGateway {
	   // fire and forget
    void sendCommand(MyPayloadType command);

    // method that attaches meta data and will wait for a result for 10 seconds
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    ReturnValue sendCommandAndWaitForAResult(MyPayloadType command, 
                                             @MetaData("userId") String userId);

    // alternative that throws exceptions on timeout
    @Timeout(value = 20, unit = TimeUnit.SECONDS)
    ReturnValue sendCommandAndWaitForAResult(MyPayloadType command) 
                         throws TimeoutException, InterruptedException;

    // this method will also wait, caller decides how long
    void sendCommandAndWait(MyPayloadType command, long timeout, TimeUnit unit) 
                         throws TimeoutException, InterruptedException;
}
