package sguest.jeimultiblocks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.bus.api.SubscribeEvent;

import java.util.concurrent.Callable;

@EventBusSubscriber(modid = JeiMultiblocks.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class JeiMultiblocksEventHandler {

    private static Callable<Boolean> callback = null;

    public static void registerCallback(Callable<Boolean> callable) {
        callback = callable;
    }

    @SubscribeEvent
    public static void gameTicked(ClientTickEvent.Pre event) {
        if(callback == null) return;
        try {
            callback.call();
            callback = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}