package com.amazonaws.http.timers.client;

import static com.amazonaws.http.timers.TimeoutTestConstants.CLIENT_EXECUTION_TIMEOUT;
import static com.amazonaws.http.timers.TimeoutTestConstants.SLOW_REQUEST_HANDLER_TIMEOUT;
import static com.amazonaws.http.timers.TimeoutTestConstants.TEST_TIMEOUT;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.TestPreConditions;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.http.AmazonHttpClient;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.MockServerTestBase;
import com.amazonaws.http.request.RequestHandlerTestUtils;
import com.amazonaws.http.request.SlowRequestHandler;
import com.amazonaws.http.response.NullErrorResponseHandler;
import com.amazonaws.http.response.NullResponseHandler;
import com.amazonaws.http.response.UnresponsiveErrorResponseHandler;
import com.amazonaws.http.server.MockServer;

/**
 * Tests that use a server that returns a predetermined error response within the timeout limit
 */
public class DummyErrorResponseServerTests extends MockServerTestBase {

    private static final int STATUS_CODE = 500;
    private AmazonHttpClient httpClient;

    @BeforeClass
    public static void preConditions() {
        TestPreConditions.assumeNotJava6();
    }

    @Override
    protected MockServer buildMockServer() {
        return new MockServer(
                MockServer.DummyResponseServerBehavior.build(STATUS_CODE, "Internal Server Failure", "Dummy response"));
    }

    @Test(timeout = TEST_TIMEOUT, expected = ClientExecutionTimeoutException.class)
    public void clientExecutionTimeoutEnabled_SlowErrorResponseHandler_ThrowsClientExecutionTimeoutException()
            throws Exception {
        httpClient = new AmazonHttpClient(
                new ClientConfiguration().withClientExecutionTimeout(CLIENT_EXECUTION_TIMEOUT));

        httpClient.execute(newGetRequest(), new NullResponseHandler(), new UnresponsiveErrorResponseHandler(),
                new ExecutionContext());
    }

    @Test(timeout = TEST_TIMEOUT, expected = ClientExecutionTimeoutException.class)
    public void clientExecutionTimeoutEnabled_SlowAfterErrorRequestHandler_ThrowsClientExecutionTimeoutException()
            throws Exception {
        httpClient = new AmazonHttpClient(
                new ClientConfiguration().withClientExecutionTimeout(CLIENT_EXECUTION_TIMEOUT));

        List<RequestHandler2> requestHandlers = RequestHandlerTestUtils.buildRequestHandlerList(
                new SlowRequestHandler().withAfterErrorWaitInSeconds(SLOW_REQUEST_HANDLER_TIMEOUT));

        httpClient.execute(newGetRequest(), new NullResponseHandler(), new NullErrorResponseHandler(),
                new ExecutionContext(requestHandlers, false, null));
    }

}
