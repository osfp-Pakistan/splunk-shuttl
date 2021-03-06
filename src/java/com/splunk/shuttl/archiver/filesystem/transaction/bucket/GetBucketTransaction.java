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
package com.splunk.shuttl.archiver.filesystem.transaction.bucket;

import java.io.File;
import java.io.IOException;

import com.splunk.shuttl.archiver.filesystem.transaction.AbstractTransaction;
import com.splunk.shuttl.archiver.filesystem.transaction.HasFileStructure;
import com.splunk.shuttl.archiver.filesystem.transaction.LocalTransactionalFileSystemFactory;
import com.splunk.shuttl.archiver.filesystem.transaction.TransactionalFileSystem;
import com.splunk.shuttl.archiver.model.Bucket;

/**
 *
 */
public class GetBucketTransaction extends AbstractTransaction<Bucket> {

	private final TransfersBuckets transfersData;

	private GetBucketTransaction(TransfersBuckets transfersData,
			HasFileStructure hasFileStructure,
			BucketTransactionCleaner transactionCleaner, Bucket data, String temp,
			String dst) {
		super(hasFileStructure, transactionCleaner, data, temp, dst);
		this.transfersData = transfersData;
	}

	@Override
	protected void doTransferData(Bucket data, String temp, String dst)
			throws IOException {
		transfersData.get(data, new File(temp), new File(dst));
	}

	public static GetBucketTransaction create(TransactionalFileSystem fs,
			Bucket src, String temp, String dst) {
		return new GetBucketTransaction(fs.getBucketTransferer(),
				LocalTransactionalFileSystemFactory.create(),
				fs.getBucketTransactionCleaner(), src, temp, dst);
	}
}
