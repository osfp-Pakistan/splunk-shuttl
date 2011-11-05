// MetricsTimeout.java
//
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

package com.splunk.connector.util;


public class MetricsTimeout extends PeriodicTimeout {
	public MetricsTimeout(int periodInMs) {
		super(periodInMs);
	}

	@Override
	public boolean runPeriodicTask() throws Exception {
		MetricsManager.getInstance().fire();
		return true;
	}
}
