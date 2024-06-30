package sguest.jeimultiblocks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.event.TickEvent.ClientTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import java.util.concurrent.Callable;

@Mod.EventBusSubscriber(modid = JeiMultiblocks.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class JeiMultiblocksEventHandler {

    private static Callable<Boolean> callback = null;

    public static void registerCallback(Callable<Boolean> callable) {
        callback = callable;
    }

    @SubscribeEvent
    public static void gameTicked(ClientTickEvent event) {
        if(callback == null || event.side.isServer()) return;
        try {
            callback.call();
            callback = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}