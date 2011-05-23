/*
 * Copyright 2010 FatWire Corporation. All Rights Reserved.
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
package com.fatwire.gst.foundation.tagging;

import COM.FutureTense.Interfaces.ICS;

import com.fatwire.assetapi.data.AssetId;
import com.fatwire.gst.foundation.facade.ics.ICSFactory;
import com.fatwire.gst.foundation.facade.install.AssetListenerInstall;
import com.openmarket.basic.event.AbstractAssetEventListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Sends requests to the tagging service.
 * 
 * @author Tony Field
 * @since Jul 28, 2010
 */
public final class TaggedAssetEventListener extends AbstractAssetEventListener {

    private static final Log LOG = LogFactory.getLog("com.fatwire.gst.foundation.tagging");

    private final AssetTaggingService svc;

    public TaggedAssetEventListener() {
        try {
            svc = AssetTaggingServiceFactory.getService(ICSFactory.getOrCreateICS());
        } catch (Exception e) {
            throw new IllegalStateException("Could not create ICS", e);
        }
    }

    @Override
    public void assetAdded(AssetId assetId) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Heard assetAdded event for " + assetId);
        }
        svc.addAsset(assetId);
    }

    @Override
    public void assetUpdated(AssetId assetId) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Heard assetUpdated event for " + assetId);
        }
        svc.updateAsset(assetId);
    }

    @Override
    public void assetDeleted(AssetId assetId) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Heard assetDeleted event for " + assetId);
        }
        svc.deleteAsset(assetId);
    }

    /**
     * Install self into AssetListener_reg table
     */
    public void install(ICS ics) {
        AssetListenerInstall.register(ics, TaggedAssetEventListener.class.getName(), true);
    }

}
