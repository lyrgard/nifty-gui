/*
 * Copyright (c) 2014, Jens Hohmuth 
 * All rights reserved. 
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are 
 * met: 
 * 
 *  * Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer. 
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF 
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.lessvoid.nifty.internal.render.sync;

import de.lessvoid.nifty.internal.InternalNiftyNode;
import de.lessvoid.nifty.internal.render.RenderNode;
import de.lessvoid.nifty.spi.NiftyRenderDevice;
import de.lessvoid.nifty.spi.NiftyRenderDevice.FilterMode;

public class RendererNodeSyncNodeFactory {
  // we need to create render resources like textures we'll keep an NiftyRenderDevice around as well
  private final NiftyRenderDevice renderDevice;

  public RendererNodeSyncNodeFactory(final NiftyRenderDevice renderDevice) {
    this.renderDevice = renderDevice;
  }

  RenderNode createRenderNode(final InternalNiftyNode node) {
    RenderNode renderNode = new RenderNode(
        node.getId().hashCode(),
        node.getLocalTransformation(),
        node.getWidth(),
        node.getHeight(),
        node.getCanvas().getCommands(),
        renderDevice.createTexture(node.getWidth(), node.getHeight(), FilterMode.Linear),
        renderDevice.createTexture(node.getWidth(), node.getHeight(), FilterMode.Linear),
        node.getCompositeOperation(),
        node.getRenderOrder());

    int indexInParent = 0;
    for (int i=0; i<node.getChildren().size(); i++) {
      InternalNiftyNode srcNode = node.getChildren().get(i);

      // we don't add nodes with zero width or height
      if (srcNode.getWidth() <= 0 || srcNode.getHeight() <= 0) {
        continue;
      }

      RenderNode childRenderNode = createRenderNode(srcNode);
      childRenderNode.setIndexInParent(indexInParent++);

      renderNode.addChildNode(childRenderNode);
    }

    return renderNode;
  }
}