/*
 * Copyright (c) 2016 Uber Technologies, Inc. (hoodie-dev-group@uber.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.hoodie.common;

import org.junit.Test;

import java.io.*;

public class

TestBloomFilter {
    @Test
    public void testAddKey() {
        BloomFilter filter = new BloomFilter(100, 0.0000001);
        filter.add("key1");
        assert (filter.mightContain("key1"));
    }

    @Test
    public void testSerialize() throws IOException, ClassNotFoundException {
        BloomFilter filter = new BloomFilter(1000, 0.0000001);
        filter.add("key1");
        filter.add("key2");
        String filterStr = filter.serializeToString();

        // Rebuild
        BloomFilter newFilter = new BloomFilter(filterStr);
        assert (newFilter.mightContain("key1"));
        assert (newFilter.mightContain("key2"));
    }
}
