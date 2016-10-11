/*
 * Copyright (c) 2015 FUJI Goro (gfx).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.gfx.android.orma.example.orma;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * To demonstrate multiple associations to the same model.
 *
 * @see Item2_Schema
 */
@Table
public class Item2 {

    @PrimaryKey
    public final String name;

    @Column(indexed = true)
    public final Category category1;

    @Nullable
    @Column(indexed = true)
    public final Category category2;

    @Setter
    public Item2(@NonNull String name, @NonNull Category category1, @Nullable Category category2) {
        this.name = name;
        this.category1 = category1;
        this.category2 = category2;
    }
}
