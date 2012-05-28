// Copyright (C) 2011 Splunk Inc.
//
// Splunk Inc. licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.splunk.shuttl.archiver.thaw;

import java.util.Map;

import com.splunk.shuttl.archiver.util.SplunkEnvironment;

/**
 * 
 */
public abstract class SplunkTool {

    private static final String TOOL_LOCATION = "/bin/";

    public abstract String getToolName();

    /**
     * @return command for executing Splunk import tool.
     */
    public String getExecutableCommand() {
	return SplunkEnvironment.getSplunkHome() + TOOL_LOCATION
		+ getToolName();
    }

    /**
     * @return the environment needed to run the command.
     */
    public Map<String, String> getEnvironment() {
	return SplunkEnvironment.getEnvironment();
    }

}