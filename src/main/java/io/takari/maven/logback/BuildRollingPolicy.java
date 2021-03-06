/*
 * Copyright (c) 2015-2017 salesforce.com All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package io.takari.maven.logback;

import java.io.File;

import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.TriggeringPolicy;

/**
 * {@link FixedWindowRollingPolicy} that triggers log file rollover on each build.
 * 
 * <pre>
 * {@code
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>build.log</file>
      <rollingPolicy class="io.takari.maven.logback.BuildRollingPolicy">
        <fileNamePattern>build-%i.log</fileNamePattern>
        <maxIndex>2</maxIndex>
      </rollingPolicy>
      <encoder>
        <pattern>%date %level %msg%n</pattern>
      </encoder>
    </appender>
 * }
 */
public class BuildRollingPolicy<E> extends FixedWindowRollingPolicy implements TriggeringPolicy<E> {

  @Override
  public boolean isTriggeringEvent(File activeFile, E event) {
    return false;
  }

  @Override
  public void start() {
    boolean rollover = new File(getActiveFileName()).exists();
    super.start();
    if (rollover) {
      rollover();
    }
  }
}
