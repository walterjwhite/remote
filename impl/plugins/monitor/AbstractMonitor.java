package com.walterjwhite.remote.impl.service.monitor;

import java.util.concurrent.Callable;

/**
 * Base class for tailing server logs polling the database for statistics, etc.
 *
 * @param <ResultType> String for tailing log files, Tuple / Object for displays
 */
public abstract class AbstractMonitor<ResultType> implements Callable<Void> {

  // = 0 :none
  // > 0 :time in milliseconds
  protected int refreshInterval;

  protected ResultType result;

  protected AbstractMonitor(final int refreshInterval, final ResultType result) {
    super();

    this.refreshInterval = refreshInterval;
    this.result = result;
  }

  protected AbstractMonitor(final ResultType result) {
    super();

    this.result = result;
  }

  public int getRefreshInterval() {
    return refreshInterval;
  }

  public void setRefreshInterval(int refreshInterval) {
    this.refreshInterval = refreshInterval;
  }

  public ResultType getResult() {
    return result;
  }

  public void setResult(ResultType result) {
    this.result = result;
  }
}
