/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.oozie.server;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.oozie.service.ConfigurationService;

public class WebRootResourceLocator {
    private static final String WEBROOT_INDEX = "/webapp/";
    private static final String WEBROOT_NO_UI_INDEX = "/webapp/no-ui";

    public URI getWebRootResourceUri() throws FileNotFoundException, URISyntaxException
    {
        URL indexUri;
        if (ConfigurationService.getBoolean("oozie.ui.enabled", true)) {
            indexUri = JspHandler.class.getResource(WebRootResourceLocator.WEBROOT_INDEX);
        } else {
            indexUri = JspHandler.class.getResource(WebRootResourceLocator.WEBROOT_NO_UI_INDEX);
        }
        if (indexUri == null) {
            throw new FileNotFoundException("Unable to find resource " + WebRootResourceLocator.WEBROOT_INDEX);
        }
        // Points to wherever /webroot/ (the resource) is
        return indexUri.toURI();
    }
}
