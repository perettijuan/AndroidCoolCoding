package com.jpp.foods.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.jpp.foods.Constants;
import com.jpp.foods.Logger;

/**
 * Manager to handle the API-REST communication via HTTP commands. <br>
 * Uses {@link HttpsURLConnection} to handle communication in a secure way.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class HttpManager {


    /**
     * Builder class for this {@link HttpManager}
     * 
     * @author Juan P. Peretti (juan.peretti@tallertechnologies.com)
     * 
     */
    public static class Builder implements Serializable {


        private static final long serialVersionUID = 4675862330290427657L;
        // *Required
        private RequestTypes mRequestType;
        // *Required
        private String mUrlAsString;
        // * Required
        private String mAccessToken;

        private Object mBodyValue;

        private HttpsURLConnection mHttpsUrlConnection;

        /**
         * Class constructor
         */
        public Builder() {

        }

        /**
         * Set the body value to send in case that this is a POST
         * 
         * @param body
         *            - the body to send in the POST
         * @return - this {@link Builder} instance
         */
        public Builder bodyValue(Object body) {
            mBodyValue = body;
            return this;
        }

        /**
         * Set the {@link HttpsURLConnection} to be used
         * 
         * @param conn
         *            - the {@link HttpsURLConnection} to use
         * @return - the Builder instance
         */
        public Builder httpsConnection(HttpsURLConnection conn) {
            mHttpsUrlConnection = conn;
            return this;
        }

        /**
         * Executes the build. If any of the parameters is null, then an
         * {@link IllegalArgumentException} is thrown
         * 
         * @param type
         *            - the {@link RequestTypes} that identifies the request
         * @param urlAsString
         *            - the URL to point as a String value
         * @return - a {@link HttpManager} instance ready to call
         *         {@link HttpManager#execute(Class)}
         */
        public HttpManager build(RequestTypes type, String urlAsString, String accessToken) {
            mUrlAsString = urlAsString;
            mRequestType = type;
            mAccessToken = accessToken;
            if (mRequestType == null) {
                throw new IllegalArgumentException(
                        "You need to provide a RequestType to create an instance");
            } else if (mUrlAsString == null) {
                throw new IllegalArgumentException(
                        "You need to provide a file protocol HTTP to create an instance");
            } else if (mAccessToken == null) {
                throw new IllegalArgumentException(
                        "You need to provide an access token");
            } else {
                return new HttpManager(this);
            }
        }

    }

    /**
     * An enum with all the possible execution types.
     * 
     * @author Juan P. Peretti (juan.peretti@tallertechnologies.com)
     * 
     */
    public static enum RequestTypes {

        GET("GET"),
        POST("POST"),
        PUT("PUT");


        private String requestName;

        RequestTypes(String name) {
            requestName = name;
        }

        public String getType() {
            return requestName;
        }

    }

    private static final String CONTENT_ACCEPT_TAG = "Content-Type";
    private static final String CONTENT_TYPE_TAG = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_LENGTH_ZERO = "0";
    private static final String AUTHORIZATION_TAG = "Authorization";

    /**
     * Buffer size use for reading
     */
    private static final int BUFFER_SIZE = 8096;
    public static final int CONNECTION_READ_TIMOUT = 10000;
    public static final int CONNECTION_TIMEOUT = 10000;

    private byte[] buffer = new byte[BUFFER_SIZE];
    private StringBuilder contents = new StringBuilder();

    // class members
    private URL mUrlValue;
    private HttpsURLConnection mConnection;

    private RequestTypes mRequestType;
    private String mUrl;
    private Object mBody;
    private JsonParser mParser;
    private String mAccessToken;

    /**
     * Private constructor to avoid external instanciation
     * 
     * @param builder
     *            - the {@link Builder} that will build this instance
     */
    private HttpManager(Builder builder) {
        mRequestType = builder.mRequestType;
        mBody = builder.mBodyValue;
        mUrl = builder.mUrlAsString;
        mConnection = builder.mHttpsUrlConnection;
        mParser = JsonParser.INSTANCE;
        mAccessToken = builder.mAccessToken;
    }

    /**
     * Executes the request to the back-end
     * 
     * @param clazz
     *            - the class type to parse the response to
     * @return - an object parse of type clazz that represents the response
     * @throws ServiceException
     *             - thrown if any problem occurs during the execution
     */
    public <T> T execute(Class<T> clazz) throws ServiceException {

        InputStream input = null;
        T returnValue = null;

        int response = ApiMessageEnum.ERROR_GENERAL_CODE;

        try {
            mUrlValue = new URL(mUrl);

            if (Constants.FOR_TESTING) {
                String value = mUrlValue.toString();
                Logger.debug("Hitting url --> " + value, this);
            }

            if (mConnection == null) {
                mConnection = (HttpsURLConnection) mUrlValue.openConnection();
            }

            // TODO review this values
            mConnection.setReadTimeout(CONNECTION_READ_TIMOUT);
            mConnection.setConnectTimeout(CONNECTION_TIMEOUT);

            mConnection.setRequestProperty(AUTHORIZATION_TAG, mAccessToken);

            // set the request type (POST, GET, etc.)
            mConnection.setRequestMethod(mRequestType.getType());

            switch (mRequestType) {
            case GET:
                // set the connection to input
                mConnection.setRequestProperty(CONTENT_ACCEPT_TAG,
                        CONTENT_TYPE_JSON);
                break;
            case POST:
                // set the connection to output
                mConnection.setDoOutput(true);
                if (mBody != null) {
                    mConnection.setRequestProperty(CONTENT_TYPE_TAG,
                            CONTENT_TYPE_JSON);
                } else {
                    mConnection.setRequestProperty(CONTENT_LENGTH,
                            CONTENT_LENGTH_ZERO);
                }
                break;
            case PUT:
                // set the connection to output
                mConnection.setDoOutput(true);
                mConnection.setRequestProperty(CONTENT_TYPE_TAG,
                        CONTENT_TYPE_JSON);
                break;

            default:
                throw new ServiceException(ApiMessageEnum.UNSUPORTED_OPERATION);
            }

            // connect to the end-point
            mConnection.connect();

            // execute the post if needed
            if (mBody != null) {
                OutputStream output = mConnection.getOutputStream();
                String postBody = JsonParser.INSTANCE.format(mBody);

                Logger.error("Printing JSON --> " + postBody);

                output.write(postBody.getBytes());
                output.close();
            }

            response = mConnection.getResponseCode();

            // String responseMessage = mConnection.getResponseMessage();
            boolean successfull = ApiMessageEnum.isSuccessResponse(response);

            if (!successfull) {
                throw new ServiceException(ApiMessageEnum.ERROR_GENERAL);
            }

            Logger.debug("Response after connection ===> " + response, this);

            input = mConnection.getInputStream();
            String contentAsString = readContents(input);

            if (contentAsString != null && clazz != null) {

                Logger.debug("Result after connect ======> " + contentAsString,
                        this);

                returnValue = mParser.parse(contentAsString, clazz);
            }

        } catch (MalformedURLException e) {
            if (Constants.FOR_TESTING) {
                e.printStackTrace();
            }
            throw new ServiceException(ApiMessageEnum.ERROR_GENERAL);
        } catch (IOException e) {
            if (Constants.FOR_TESTING) {
                e.printStackTrace();
            }
            throw new ServiceException(ApiMessageEnum.ERROR_GENERAL);
        } catch (Exception e) {
            if (Constants.FOR_TESTING) {
                e.printStackTrace();
            }
            throw new ServiceException(ApiMessageEnum.ERROR_GENERAL);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new ServiceException(
                            ApiMessageEnum.ERROR_OPERATION_FAIL);
                }
            }
        }

        return returnValue;
    }

    /**
     * Read the content from the input stream specified and store it in the
     * StringBuffer specified.
     * 
     * @param is
     *            The input stream from the http response.
     */
    private String readContents(InputStream is) throws IOException {
        String processedResponse = null;
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