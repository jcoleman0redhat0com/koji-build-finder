/**
 * Copyright 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.red.build.finder;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JSONUtils {
    private JSONUtils() {
        throw new AssertionError();
    }

    public static String dumpString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

        try {
            String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean dumpFile(File file, Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String, Collection<String>> loadChecksumsFile(File file)  {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String, List<String>>> typeRef = new TypeReference<Map<String, List<String>>>() {

        };

        try {
            Map<String, Collection<String>> obj = mapper.readValue(file, typeRef);
            return obj;
        } catch (IOException e) {
            return null;
        }
    }

   public static Map<Integer, KojiBuild> loadBuildsFile(File file) {
       ObjectMapper mapper = new ObjectMapper();
       TypeReference<Map<Integer, KojiBuild>> ref = new TypeReference<Map<Integer, KojiBuild>>() {

       };

       try {
           Map<Integer, KojiBuild> obj = mapper.readValue(file, ref);
           return obj;
       } catch (IOException e) {
           e.printStackTrace();
       }

       return null;
   }
}
