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
package com.splunk.shep.archiver.archive;

import com.splunk.shep.archiver.fileSystem.ArchiveFileSystem;
import com.splunk.shep.archiver.fileSystem.ArchiveFileSystemFactory;

/**
 * Construction code for creating BucketArchivers that archives in different
 * FileSystems.
 */
public class BucketArchiverFactory {

    /**
     * @return {@link BucketArchiver} as configured in .conf files.
     */
    public static BucketArchiver createConfiguredArchiver() {
	ArchiveConfiguration config = ArchiveConfiguration.getSharedInstance();
	return createWithConfiguration(config);
    }

    /**
     * Testability with specified configuration.
     */
    public static BucketArchiver createWithConfiguration(
	    ArchiveConfiguration config) {
	ArchiveFileSystem archiveFileSystem = ArchiveFileSystemFactory
		.getWithConfiguration(config);
	return createWithConfigurationAndArchiveFileSystem(config,
		archiveFileSystem);
    }

    /**
     * Testability with both configuration and archive file system.
     */
    public static BucketArchiver createWithConfigurationAndArchiveFileSystem(
	    ArchiveConfiguration config, ArchiveFileSystem archiveFileSystem) {
	SplunkExportTool splunkExportTool = new SplunkExportTool();
	return new BucketArchiver(config, new BucketExporter(splunkExportTool),
		new PathResolver(config), new ArchiveBucketTransferer(
			archiveFileSystem));

    }
}