/*
 * Copyright 2012-2015 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.fess.app.web.admin.keymatch;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.codelibs.fess.app.web.CrudMode;
import org.codelibs.fess.util.ComponentUtil;
import org.lastaflute.web.validation.Required;
import org.lastaflute.web.validation.theme.conversion.ValidateTypeFailure;

/**
 * @author codelibs
 * @author jflute
 */
public class CreateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public int crudMode;

    @Required
    @Size(max = 100)
    public String term;

    @Required
    @Size(max = 4000)
    public String query;

    @Required
    @Min(value = 0)
    @Max(value = 2147483647)
    @ValidateTypeFailure
    public Integer maxSize;

    @Required
    @ValidateTypeFailure
    public Float boost;

    @Required
    @Size(max = 255)
    public String createdBy;

    @Required
    @ValidateTypeFailure
    public Long createdTime;

    public void initialize() {
        crudMode = CrudMode.CREATE;
        maxSize = 10;
        boost = 100.0f;
        createdBy = ComponentUtil.getSystemHelper().getUsername();
        createdTime = ComponentUtil.getSystemHelper().getCurrentTimeAsLong();

    }
}