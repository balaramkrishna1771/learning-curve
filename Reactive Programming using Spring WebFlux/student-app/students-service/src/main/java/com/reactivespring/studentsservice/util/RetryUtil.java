package com.reactivespring.studentsservice.util;

import com.reactivespring.studentsservice.exception.PaymentsServerException;
import com.reactivespring.studentsservice.exception.StudentInfoServerException;
import reactor.core.Exceptions;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryUtil {

    public static Retry retrySpec(){
        return Retry.fixedDelay(3, Duration.ofSeconds(1))
                .filter(ex -> ex instanceof StudentInfoServerException || ex instanceof PaymentsServerException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure())));
    }
}
