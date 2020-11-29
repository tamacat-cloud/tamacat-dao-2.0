/*
 * Copyright (c) 2018 tamacat.org
 * All rights reserved.
 */
package cloud.tamacat.dao.util;

import java.io.Reader;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cloud.tamacat.dao.meta.Column;
import cloud.tamacat.dao.meta.DataType;
import cloud.tamacat.dao.orm.MapBasedORMappingBean;
import cloud.tamacat.log.Log;
import cloud.tamacat.log.LogFactory;
import cloud.tamacat.util.ClassUtils;
import cloud.tamacat.util.CollectionUtils;
import cloud.tamacat.util.DateUtils;
import cloud.tamacat.util.StringUtils;

public class JSONUtils {

	static final Log LOG = LogFactory.getLog(JSONUtils.class);
	
	public static String toString(MapBasedORMappingBean<?> bean, Column... columns) {
		return json(bean, columns).toString();
	}
	
	public static String toString(Collection<? extends MapBasedORMappingBean<?>> list, Column... columns) {
		return json(list, columns).toString();
	}
	
	public static JsonObject json(MapBasedORMappingBean<?> bean, Column... columns) {
		JsonObject json = new JsonObject();
		for (Column col : columns) {
			String value = bean.val(col);
			if (value == null) value = "";
			json.addProperty(col.getColumnName(), value);
		}
		return json;
	}

	/**
	 * Not included empty value.
	 * @since 1.4
	 * @param bean
	 * @param columns
	 */
	public static JsonObject toJson(MapBasedORMappingBean<?> bean, Column... columns) {
		JsonObject json = new JsonObject();
		for (Column col : columns) {
			String value = bean.val(col);
			if (value != null && value.length()>0) {
				if (col.getType()==DataType.NUMERIC) {
					json.addProperty(col.getColumnName(), StringUtils.parse(value, 0L));
				} else if (col.getType()==DataType.FLOAT) {
					json.addProperty(col.getColumnName(), StringUtils.parse(value, 0d));
				} else if (col.getType()==DataType.TIME) {
					String format = col.getFormat();					
					if (StringUtils.isNotEmpty(format)) {
						Date d = DateUtils.parse(value, format);
						if (d != null) {
							json.addProperty(col.getColumnName(), d.getTime()); //DateUtils.getTime(d, format));
						} else {
							json.addProperty(col.getColumnName(), value);
						}
					} else {
						if (value.indexOf('.')>0) {
							json.addProperty(col.getColumnName(), DateUtils.parse(value, "yyyy-MM-dd HH:mm:ss.SSS").getTime());
						} else {
							json.addProperty(col.getColumnName(), DateUtils.parse(value, "yyyy-MM-dd HH:mm:ss").getTime());
						}
					}
				} else if (col.getType()==DataType.DATE) {
					json.addProperty(col.getColumnName(), DateUtils.parse(value, "yyyy-MM-dd").getTime());
				} else {
					json.addProperty(col.getColumnName(), value);
				}
			}
		}
		return json;
	}
	
	public static JsonArray json(Collection<? extends MapBasedORMappingBean<?>> list, Column... columns) {
		JsonArray json = new JsonArray();
		for (MapBasedORMappingBean<?> bean : list) {
			json.add(json(bean, columns));
		}
		return json;
	}
	
	public static Object parse(JsonElement el, Column col) {
		if (DataType.FLOAT == col.getType()) {
			return el.getAsBigDecimal();
		} else if (DataType.NUMERIC == col.getType()) {
			return el.getAsLong();
		} else if (DataType.DATE == col.getType() || DataType.TIME == col.getType()) {
			return new Date(el.getAsLong());
		} else if (DataType.BOOLEAN == col.getType()) {
			return el.getAsBoolean();
		} else {
			return el.getAsString();
		}
	}
	
	public static <T extends MapBasedORMappingBean<?>> T parse(T bean, JsonObject json, Column... columns) {
		for(Column col : columns) {
			JsonElement el = json.get(col.getName());
			if (el != null) {
				bean.val(col, parse(el, col));
			}
		}
		return bean;
	}
	
	public static <T extends MapBasedORMappingBean<?>> T parse(T bean, Reader reader, Column... columns) {
		return parse(bean, JsonParser.parseReader(reader).getAsJsonObject(), columns);
	}
	
	public static <T extends MapBasedORMappingBean<?>> Collection<T> parseArray(Reader reader, Class<T> type, Column... columns) {
		Collection<T> list = CollectionUtils.newArrayList();
		Map<String, Column> colmaps = CollectionUtils.newLinkedHashMap();
		for (Column col : columns) {
			colmaps.put(col.getColumnName(), col);
		}
		JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
		for (JsonElement json : array) {
			try {
				T data = ClassUtils.newInstance(type);
				//T data = type.getDeclaredConstructor().newInstance();
				if (json instanceof JsonObject) {
					list.add(parse(data, (JsonObject) json, columns));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return list;
	}
	
	/*
	public static <T extends MapBasedORMappingBean<?>> T parse(T bean, JsonParser parser, Column... columns) {
		Map<String, Column> colmaps = CollectionUtils.newLinkedHashMap();
		for (Column col : columns) {
			colmaps.put(col.getColumnName(), col);
		}
		Column col = null;
		while (parser.hasNext()) {
			Event event = parser.next();
			switch (event) {
				case KEY_NAME:
					String key = parser.getString();
					if (StringUtils.isNotEmpty(key)) {
						col = colmaps.get(key);
					}
					break;
				case VALUE_STRING:
					if (col != null) {
						bean.val(col, parser.getString());
						col = null;
					}
					break;
				case VALUE_TRUE:
					if (col != null) {
						bean.val(col, true);
						col = null;
					}
					break;
				case VALUE_FALSE:
					if (col != null) {
						bean.val(col, false);
						col = null;
					}
					break;
				case VALUE_NUMBER:
					if (col != null) {
						long value = parser.getLong();
						if (col.getType() == DataType.TIME || col.getType() == DataType.DATE) {
							bean.val(col, new Date(value));
						} else {
							bean.val(col, value);
						}
						col = null;
					}
					break;
				case VALUE_NULL:
					if (col != null) {
						bean.val(col, "");
						col = null;
					}
					break;
				case START_ARRAY:
				case END_ARRAY:
				case START_OBJECT:
				case END_OBJECT:
					break;
				default:
					LOG.warn("JSON parser unknown event: "+event);
					col = null;
					break;
			}
		}
		return bean;
	}
	
	public static <T extends MapBasedORMappingBean<?>> Collection<T> parseArray(JsonParser parser, Class<T> type, Column... columns) {
		Collection<T> list = CollectionUtils.newArrayList();
		Map<String, Column> colmaps = CollectionUtils.newLinkedHashMap();
		for (Column col : columns) {
			colmaps.put(col.getColumnName(), col);
		}
		T data = null;
		Column col = null;
		while (parser.hasNext()) {
			Event event = parser.next();
			switch (event) {
				case KEY_NAME:
					String key = parser.getString();
					if (StringUtils.isNotEmpty(key)) {
						col = colmaps.get(key);
					}
					break;
				case VALUE_STRING:
					if (col != null) {
						String value = parser.getString();
						data.val(col, value);
						LOG.trace("    \""+col.getColumnName()+"\":\""+value+"\"");
						col = null;
					}
					break;
				case VALUE_TRUE:
					if (col != null) {
						data.val(col, true);
						LOG.trace("    \""+col.getColumnName()+"\":true");
						col = null;
					}
					break;
				case VALUE_FALSE:
					if (col != null) {
						data.val(col, false);
						LOG.trace("    \""+col.getColumnName()+"\":false");
						col = null;
					}
					break;
				case VALUE_NUMBER:
					if (col != null) {
						long value = parser.getLong();
						if (col.getType() == DataType.TIME || col.getType() == DataType.DATE) {
							data.val(col, new Date(value));
						} else {
							data.val(col, value);
						}
						LOG.trace("    \""+col.getColumnName()+"\":\""+value+"\"");
						col = null;
					}
					break;
				case VALUE_NULL:
					if (col != null) {
						data.val(col, "");
						LOG.trace("    \""+col.getColumnName()+"\":\"\"");
						col = null;
					}
					break;
				case START_ARRAY:
					LOG.trace("[");
					break;
				case END_ARRAY:
					LOG.trace("]");
					break;
				case START_OBJECT:
					data = ClassUtils.newInstance(type);
					LOG.trace("  {");
					break;
				case END_OBJECT:
					list.add(data);
					data = null;
					LOG.trace("  }");
					break;
				default:
					LOG.warn("JSON parser unknown event: "+event);
					col = null;
					break;
			}
		}
		return list;
	}
	*/
}
