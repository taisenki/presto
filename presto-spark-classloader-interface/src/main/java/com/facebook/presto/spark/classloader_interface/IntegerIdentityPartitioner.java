/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.spark.classloader_interface;

import org.apache.spark.Partitioner;

import static java.util.Objects.requireNonNull;

public class IntegerIdentityPartitioner
        extends Partitioner
{
    private final int numPartitions;

    public IntegerIdentityPartitioner(int numPartitions)
    {
        this.numPartitions = numPartitions;
    }

    @Override
    public int numPartitions()
    {
        return numPartitions;
    }

    @Override
    public int getPartition(Object key)
    {
        int partition = requireNonNull((Integer) key, "key is null");
        if (!(partition >= 0 && partition < numPartitions)) {
            throw new IllegalArgumentException("invalid partition: %s" + partition);
        }
        return partition;
    }
}
