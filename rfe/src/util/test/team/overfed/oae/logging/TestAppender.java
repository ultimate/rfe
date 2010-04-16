package team.overfed.oae.logging;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class TestAppender implements Appender
{
	private Layout layout;
	private ErrorHandler errorHandler;
	private String name;
	private List<Filter> filters;
	private StringBuilder sb;

	public TestAppender()
	{
		this.clearFilters();
		sb = new StringBuilder();
	}

	@Override
	public void addFilter(Filter filter)
	{
		filters.add(filter);
	}

	@Override
	public void clearFilters()
	{
		this.filters = new ArrayList<Filter>();
	}

	@Override
	public void close()
	{
	}

	@Override
	public void doAppend(LoggingEvent event)
	{
		if(layout == null)
			sb.append(event.getLevel() + " [" + event.getThreadName() + "] " + event.getLoggerName() + "."
					+ event.getLocationInformation().getMethodName() + "(" + event.getLocationInformation().getLineNumber() + ") | "
					+ event.getMessage() + "\n");
		else
			sb.append(layout.format(event));
	}

	@Override
	public ErrorHandler getErrorHandler()
	{
		return this.errorHandler;
	}

	@Override
	public Filter getFilter()
	{
		return this.filters.get(0);
	}

	@Override
	public Layout getLayout()
	{
		return this.layout;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public boolean requiresLayout()
	{
		return false;
	}

	@Override
	public void setErrorHandler(ErrorHandler errorHandler)
	{
		this.errorHandler = errorHandler;
	}

	@Override
	public void setLayout(Layout layout)
	{
		this.layout = layout;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	public String getLoggingHistory()
	{
		return sb.toString();
	}
}
