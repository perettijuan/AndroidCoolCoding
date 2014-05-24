package com.jpp.foods.services;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A wrapper created around the Gson Library to parse JSON objects.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
class JsonParser {

	private Gson mGsonManager;

	static JsonParser INSTANCE = new JsonParser();

	private JsonParser() {
		GsonBuilder builder = new GsonBuilder();
		mGsonManager = builder.create();
	}

	/**
	 * Parse an object to string format
	 * 
	 * @param obj
	 * @return
	 */
	public String format(Object obj) {
		return mGsonManager.toJson(obj);
	}

	/**
	 * Parse string data to Type instance
	 * 
	 * @param data
	 * @param type
	 * @return
	 */
	public Object parse(String data, Type type) {
		return mGsonManager.fromJson(data, type);
	}

	/**
	 * Parse string data to clazz instance
	 * 
	 * @param data
	 *            Formated data
	 * @param clazz
	 *            result type clazz
	 * @return
	 */
	public <T> T parse(String data, Class<T> clazz) {
		return mGsonManager.fromJson(data, clazz);
	}

}
