package net.ims.nvidiafix.mixin;

import net.optifine.shaders.ShadersFramebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.nio.IntBuffer;

@Mixin(
        targets = {"net.optifine.shaders.ShadersFramebuffer"},
        remap = false
)
public interface ShadersFramebufferAccess {
    @Accessor
    IntBuffer getDepthTextures();
}
