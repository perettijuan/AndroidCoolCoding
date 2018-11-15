package com.jpp.codingex.background;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Takes care of the access to any Http connection needed.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class HttpManager {

	private static final int CONNECTION_READ_TIMOUT = 10000;
	private static final int CONNECTION_TIMEOUT = 10000;
	private static final String GET_REQUEST_METHOD = "GET";
	private static final int BUFFER_SIZE = 8096;

	/**
	 * Executes the access to the given URL.
	 * 
	 * @param url
	 *            - the URL to access.
	 * @return - a String that contains the result of the access. If any error
	 *         occurs, then the return value will be null.
	 */
	static String execute(String url) {

		InputStream input = null;
		URL urlValue;
		HttpURLConnection connection;
		String result = null;

		try {
			if (url == null) {
				throw new IllegalArgumentException(
						"The given URL cannot be null");
			}
			// Prepare the connection to the end-point
			urlValue = new URL(url);

			connection = (HttpURLConnection) urlValue.openConnection();
			connection.setReadTimeout(CONNECTION_READ_TIMOUT);
			connection.setConnectTimeout(CONNECTION_TIMEOUT);
			connection.setRequestMethod(GET_REQUEST_METHOD);

			// connect to the end-point
			connection.connect();

			// get the request result
			input = connection.getInputStream();

			// Read the contents
			result = readContents(input);

		} catch (MalformedURLException e) {
			// do nothing, just return a null value
		} catch (IOException e) {
			// do nothing, just return a null value
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// do nothing, just return a null value
				}
			}

		}

		return result;
	}

	/**
	 * Read the content from the input stream specified and store it in the
	 * StringBuffer specified.
	 * 
	 * @param is
	 *            The input stream from the http response.
	 */
	private static String readContents(InputStream is) throws IOException {
		String processedResponse = null;
		byte[] buffer = new byte[BUFFER_SIZE];
		StringBuilder contents = new StringBuilder();
		if (is != null) {
			contents.delete(0, contents.length());
			int readBytes = 0;
			while ((readBytes = is.read(buffer, 0, BUFFER_SIZE)) > 0) {
				contents.append(new String(buffer, 0, readBytes));
			}
			processedResponse = contents.toString();
		}
		return processedResponse;
	}
}
