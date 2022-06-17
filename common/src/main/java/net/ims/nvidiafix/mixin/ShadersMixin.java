package net.ims.nvidiafix.mixin;

import net.optifine.shaders.ShadersFramebuffer;
import org.lwjgl.opengl.GL43C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(
        targets = {"net.optifine.shaders.Shaders"},
        remap = false
)
public class ShadersMixin {
    @Shadow private static ShadersFramebuffer sfb;

    @Shadow private static ShadersFramebuffer dfb;

    @Redirect(method = "preRenderHand", remap = false, at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL43;glCopyTexSubImage2D(IIIIIIII)V"))
    private static void redirectCopy(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GL43C.glCopyImageSubData(
                ((ShadersFramebufferAccess) dfb).getDepthTextures().get(0),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                ((ShadersFramebufferAccess) dfb).getDepthTextures().get(2),
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

    @Redirect(method = "preWater", remap = false, at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL43;glCopyTexSubImage2D(IIIIIIII)V"))
    private static void redirectCopy2(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GL43C.glCopyImageSubData(
                ((ShadersFramebufferAccess) dfb).getDepthTextures().get(0),
                GL43C.GL_TEXTURE_2D,
                0,
                0,
                0,
                0,
                ((ShadersFramebufferAccess) dfb).getDepthTextures().get(1),
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
