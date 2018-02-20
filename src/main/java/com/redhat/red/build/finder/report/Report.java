/*
 * Copyright (C) 2017 Red Hat, Inc.
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
package com.redhat.red.build.finder.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Report {
    private static final Logger LOGGER = LoggerFactory.getLogger(Report.class);

    public abstract String render();

    public boolean outputToFile(File file) {
        try {
            FileUtils.writeStringToFile(file, render(), "UTF-8", false);
        } catch (IOException e) {
            LOGGER.error("File output error", e);
            return false;
        }

        return true;
    }
}
