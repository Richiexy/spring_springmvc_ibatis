package com.iis.util;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JacksonUtil
{
  protected static final Logger log = LoggerFactory.getLogger(JacksonUtil.class);

  private static final ObjectMapper mapper = new ObjectMapper();

  public static String serializeObjectToJson(Object obj, boolean indent)
  {
    try {
      ObjectMapper mapper = new ObjectMapper();
      if(indent){
    	  return mapper.defaultPrettyPrintingWriter().writeValueAsString(obj);
      }else{
    	  return mapper.writeValueAsString(obj);
      }
      
    } catch (Exception e) {
      log.error(
        "serialize object to json", e);
    }return null;
  }

  public static void serializeObjectToFile(Object obj, File file)
  {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.defaultPrettyPrintingWriter().writeValue(file, obj);
    } catch (Exception e) {
      log.error(
        "serialize object to json", e);
    }
  }

  public static <T> T deserializeFormFile(File file, Class<T> clazz) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(file, clazz);
    } catch (Exception e) {
      log.error(
        "deserializeFormFile", e);
    }
    return null;
  }

  public static <T> T deserializeJsonToObject(String json, TypeReference<T> typeReference)
  {
    try
    {
      return mapper.readValue(json, typeReference);
    } catch (Exception e) {
      log.error(
        "deserialize json to object", e);
    }return null;
  }

  public static <T> T deserializeJsonToObject(String json, Class<T> clazz)
  {
    try
    {
      return mapper.readValue(json, clazz);
    } catch (Exception e) {
      log.error(
        "deserialize json to object", e);
    }return null;
  }

  public static Object deserializeJsonToObject(String json, JavaType jt)
  {
    try
    {
      return mapper.readValue(json, jt);
    } catch (Exception e) {
      log.error(
        "deserialize json to object", e);
    }return null;
  }

  public static <T> JavaType getListJavaType(Class<T> clazz)
  {
    TypeFactory instance = TypeFactory.defaultInstance();
    JavaType[] pt = { instance._constructType(clazz, null) };
    JavaType subtype = instance.constructSimpleType(List.class, pt);
    JavaType[] collectionParams = instance.findTypeParameters(subtype, Collection.class);
    if (collectionParams.length != 1) {
      throw new IllegalArgumentException("Could not find 1 type parameter for Collection class list (found " + collectionParams.length + ")");
    }
    JavaType jt = CollectionType.construct(List.class, collectionParams[0]);
    return jt;
  }

  public static <T> List<T> deserializeJsonToList(String json, Class<T> clazz)
  {
    JavaType jt = getListJavaType(clazz);
    try {
      return (List)mapper.readValue(json, jt);
    } catch (Exception e) {
      log.error(
        "deserialize json to object", e);
    }return null;
  }

  public static <K, V> JavaType getMapJavaType(Class<K> clazzKey, Class<V> clazzValue)
  {
    TypeFactory instance = TypeFactory.defaultInstance();
    JavaType[] pt = { instance._constructType(clazzKey, null), instance._constructType(clazzValue, null) };
    JavaType subtype = instance.constructSimpleType(Map.class, pt);
    JavaType[] mapParams = instance.findTypeParameters(subtype, Map.class);
    if (mapParams.length != 2) {
      throw new IllegalArgumentException("Could not find 2 type parameter for Map class map (found " + mapParams.length + ")");
    }
    JavaType jt = MapType.construct(Map.class, mapParams[0], mapParams[1]);
    return jt;
  }

  public static <K, V> Map<K, V> deserializeJsonToMap(String json, Class<K> clazzKey, Class<V> clazzValue)
  {
    JavaType jt = getMapJavaType(clazzKey, clazzValue);
    try {
      return (Map)mapper.readValue(json, jt);
    } catch (Exception e) {
      log.error(
        "deserialize json to object", e);
    }return null;
  }

  public static <K, V> List<Map<K, V>> deserializeJsonToListMap(String json, Class<K> clazzKey, Class<V> clazzValue)
  {
    JavaType tmp = getMapJavaType(clazzKey, clazzValue);
    TypeFactory instance = TypeFactory.defaultInstance();
    JavaType[] pt = { tmp };
    JavaType subtype = instance.constructSimpleType(List.class, pt);
    JavaType[] collectionParams = instance.findTypeParameters(subtype, Collection.class);
    if (collectionParams.length != 1) {
      throw new IllegalArgumentException("Could not find 1 type parameter for Collection class list (found " + collectionParams.length + ")");
    }
    JavaType jt = CollectionType.construct(List.class, collectionParams[0]);
    try {
      return (List)mapper.readValue(json, jt);
    } catch (Exception e) {
      log.error(
        "deserialize json to object", e);
    }return null;
  }
}