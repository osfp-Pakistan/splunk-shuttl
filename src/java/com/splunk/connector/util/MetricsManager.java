// MetricsManager.java
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

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class MetricsManager {
	private static MetricsManager instance = new MetricsManager();
	private Set<MetricsCallback> callbacks = new HashSet<MetricsCallback>();
	private Logger logger = Logger.getLogger("EventEmitter");
	public static MetricsManager getInstance() {
		return instance;
	}
	
	public void register(MetricsCallback callback) {
		synchronized (callbacks) {
			callbacks.add(callback);
		}
	}
	
	public void unregister(MetricsCallback callback) {
		synchronized (callbacks) {
			callbacks.remove(callback);
		}
	}
	
	public void fire() {
		synchronized (callbacks) {
			for (MetricsCallback c : callbacks) {
				c.generateMetrics(logger);
			}
		}
	}
}
