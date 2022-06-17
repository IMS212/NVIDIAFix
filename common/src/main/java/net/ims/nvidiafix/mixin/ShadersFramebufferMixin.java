package net.ims.nvidiafix.mixin;

import org.lwjgl.opengl.GL43C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(
        targets = {"net.optifine.shaders.ShadersRender"},
        remap = false
)
public class ShadersFramebufferMixin {
    @Redirect(method = "renderShadowMap", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glCopyTexSubImage2D(IIIIIIII)V"))
    private static void redirectCopy(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GL43C.glCopyImageSubData(
                ((ShadersFramebufferAccess) ShadersAccess.getSfb()).getDepthTextures().get(0),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                ((ShadersFramebufferAccess) ShadersAccess.getSfb()).getDepthTextures().get(1),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                width,
                height,
                1
        );
    }
}
