package net.ims.nvidiafix.fabric;

import org.spongepowered.asm.mixin.Mixins;

public class Pain implements Runnable{
    @Override
    public void run() {
        Mixins.addConfiguration("nvidiafix-common.mixins.json");
    }
}
