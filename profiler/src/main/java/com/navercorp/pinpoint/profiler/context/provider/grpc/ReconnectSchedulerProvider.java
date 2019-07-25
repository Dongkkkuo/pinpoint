/*
 * Copyright 2019 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.profiler.context.provider.grpc;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.navercorp.pinpoint.common.util.PinpointThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author Woonduk Kang(emeroad)
 */
public class ReconnectSchedulerProvider implements Provider<ScheduledExecutorService> {
    @Inject
    public ReconnectSchedulerProvider() {
    }

    @Override
    public ScheduledExecutorService get() {
        final PinpointThreadFactory threadFactory = new PinpointThreadFactory("Pinpoint-reconnect-thread");
        final ScheduledThreadPoolExecutor scheduler = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1, threadFactory);

        // not recommended
//        LoggingRejectedExecutionHandler reconnectScheduler = new LoggingRejectedExecutionHandler("ReconnectScheduler");
//        scheduler.setRejectedExecutionHandler(reconnectScheduler);


        ScheduledExecutorService scheduledExecutorService = Executors.unconfigurableScheduledExecutorService(scheduler);
        return scheduledExecutorService ;
    }

}
