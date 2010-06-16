/*
 * Copyright (c) 2009 FatWire Corporation. All Rights Reserved.
 * Title, ownership rights, and intellectual property rights in and
 * to this software remain with FatWire Corporation. This  software
 * is protected by international copyright laws and treaties, and
 * may be protected by other law.  Violation of copyright laws may
 * result in civil liability and criminal penalties.
 */
package com.fatwire.gst.foundation.facade.assetapi;

import com.fatwire.assetapi.data.AssetData;
import com.fatwire.assetapi.data.AttributeData;

import java.util.Arrays;

/**
 * Utility class for processing attribute data
 *
 * @author Tony Field
 * @since Nov 17, 2009
 */
public final class AttributeDataUtils
{
    private AttributeDataUtils() {}

    /**
     * Get the specified attribute field from the AssetData object.  If it is not found,
     * get the next field listed.  Continue until the end of the list.  Safe to use with
     * a single value.
     * If no data is found for any attribute, an exception is thrown.
     *
     * @param assetData populated AssetData object
     * @param orderedAttributeNames vararg array of attribute names that are expected to be found and populated in
     * the assetData object
     * @return the value, never null
     */
    public static String getWithFallback(AssetData assetData, String... orderedAttributeNames)
    {
        for(String attr : orderedAttributeNames)
        {
            AttributeData attrData = assetData.getAttributeData(attr);
            if(attrData.getData() != null)
            {
                return attrData.getData().toString();
            }
        }
        throw new IllegalArgumentException("Asset data [" + assetData + "] does not contain any of the specified attributes: " + Arrays.asList(orderedAttributeNames));
    }

    /**
     * Get the specified attribute data, converting each of the values into
     * a string and separating them with a comma (no space).
     *
     * @param attributeData multi-valued attribute data
     * @return attribute data converted to a string, comma-separated.
     */
    public static String getMultivaluedAsCommaSepString(AttributeData attributeData)
    {
        StringBuilder sb = new StringBuilder();
        for(Object vals : attributeData.getDataAsList())
        {
            if(sb.length() > 0)
            {
                sb.append(",");
            }
            sb.append(vals.toString());
        }
        return sb.toString();
    }
}
