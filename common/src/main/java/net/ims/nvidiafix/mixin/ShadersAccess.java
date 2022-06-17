package net.ims.nvidiafix.mixin;

import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersFramebuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(
        targets = {"net.optifine.shaders.Shaders"},
        remap = false
)
public interface ShadersAccess {
    @Accessor
    public static ShadersFramebuffer getSfb() {
        throw new AssertionError();
    }
}
