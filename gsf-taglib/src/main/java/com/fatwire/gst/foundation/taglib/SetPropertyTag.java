/*
 * Copyright 2010 Metastratus Web Solutions Limited. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fatwire.gst.foundation.taglib;

import java.io.IOException;
import javax.servlet.jsp.JspException;

import com.fatwire.gst.foundation.properties.AssetApiPropertyDao;
import com.fatwire.gst.foundation.properties.PropertyDao;

/**
 * Set the property name, optionally including the site names to add to this particular property.
 *
 * @author Tony Field
 * @since 2012-03-27
 */
public final class SetPropertyTag extends GsfSimpleTag {

    private String description;
    private String value;
    private String property;
    private String publist = null;

    @Override
    public void doTag() throws JspException, IOException {
        PropertyDao dao = AssetApiPropertyDao.getInstance(getICS());
        dao.setProperty(property, description, value);
        if (publist != null) dao.addToSite(property, publist.split(","));
        super.doTag();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setPublist(String s) {
        this.publist = s;
    }
}