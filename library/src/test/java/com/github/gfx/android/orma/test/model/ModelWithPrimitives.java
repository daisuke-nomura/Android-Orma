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
package com.github.gfx.android.orma.test.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Table;

/**
 * @see ModelWithPrimitives_Schema
 * @see ModelWithBoxTypes
 */
@Table
public class ModelWithPrimitives {

    @Column
    public boolean booleanValue;

    @Column
    public byte byteValue;

    @Column
    public short shortValue;

    @Column
    public int intValue;

    @Column
    public long longValue;

    @Column
    public float floatValue;

    @Column
    public double doubleValue;

    public static ModelWithPrimitives create(boolean booleanValue, byte byteValue, short shortValue, int intValue,
            long longValue, float floatValue, double doubleValue) {
        ModelWithPrimitives model = new ModelWithPrimitives();
        model.booleanValue = booleanValue;
        model.byteValue = byteValue;
        model.shortValue = shortValue;
        model.intValue = intValue;
        model.longValue = longValue;
        model.floatValue = floatValue;
        model.doubleValue = doubleValue;
        return model;
    }
}
